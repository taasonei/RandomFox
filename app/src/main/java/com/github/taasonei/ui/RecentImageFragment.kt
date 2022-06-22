package com.github.taasonei.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.imageLoader
import coil.request.ImageRequest
import com.github.taasonei.R
import com.github.taasonei.databinding.FragmentRecentImageBinding
import com.github.taasonei.extensions.onTouch
import com.github.taasonei.model.FoxPhoto
import com.github.taasonei.model.Status
import com.github.taasonei.viewmodel.RecentImageViewModel

class RecentImageFragment : Fragment() {

    private var _binding: FragmentRecentImageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecentImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recentImageCard.foxPhoto.adjustViewBounds = true

        val checkBox = binding.recentImageCard.likeCheckbox
        val gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    checkBox.isChecked = !checkBox.isChecked
                    return super.onDoubleTap(e)
                }
            })

        binding.recentImageCard.root.onTouch { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addToFavourites()
            } else {
                removeFromFavourites()
            }
        }

        binding.fabRandom.setOnClickListener {
            viewModel.getFoxPhoto()
        }

        viewModel.foxPhoto.observe(viewLifecycleOwner) { foxPhoto ->
            if (viewModel.status.value is Status.Success) {
                loadPhoto(foxPhoto)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            if (status is Status.Error) {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPhoto(foxPhoto: FoxPhoto) {
        val request = ImageRequest.Builder(requireContext())
            .data(foxPhoto.image)
            .listener(
                onStart = {
                    showProgressBar()
                },
                onSuccess = { _, result ->
                    hideProgressBar()
                    binding.recentImageCard.foxPhoto.setImageDrawable(result.drawable)
                },
                onError = { _, _ ->
                    hideProgressBar()
                    binding.recentImageCard.foxPhoto.setImageResource(R.drawable.ic_baseline_broken_image)
                })
            .build()

        requireContext().imageLoader.enqueue(request)
    }

    private fun showProgressBar() {
        binding.apply {
            recentImageCard.root.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        binding.apply {
            progressBar.visibility = View.GONE
            recentImageCard.root.visibility = View.VISIBLE
        }
    }

    private fun addToFavourites() {
        //TODO add to db
        Toast.makeText(requireContext(), "Added to favourites", Toast.LENGTH_SHORT).show()
    }

    private fun removeFromFavourites() {
        //TODO remove from db
        //TODO replace toast on snackbar with undo
        Toast.makeText(requireContext(), "Removed from favourites", Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentImageFragment()
    }
}
