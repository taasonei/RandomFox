package com.github.taasonei.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.imageLoader
import coil.request.ImageRequest
import com.github.taasonei.R
import com.github.taasonei.databinding.FragmentRecentImageBinding
import com.github.taasonei.extensions.onTouch
import com.github.taasonei.model.Status
import com.github.taasonei.viewmodel.RecentImageViewModel
import com.google.android.material.snackbar.Snackbar

class RecentImageFragment : Fragment() {

    private var _binding: FragmentRecentImageBinding? = null
    private val binding
        get() = _binding!!

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
                    if (checkBox.isChecked) {
                        addToFavourites()
                    } else {
                        removeFromFavourites()
                    }
                    return super.onDoubleTap(e)
                }
            })

        binding.recentImageCard.root.onTouch { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                addToFavourites()
            } else {
                removeFromFavourites()
            }
        }

        binding.fabRandom.setOnClickListener {
            viewModel.getFoxPhoto()
        }

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Error -> {
                    // TODO error toast
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()

                    if (binding.recentImageCard.foxPhoto.drawable == null) {
                        loadPhoto("https://randomfox.ca/images/50.jpg")
                        //binding.recentImageCard.likeCheckbox.isChecked = foxPhoto.isFavourite
                    }
                }
                is Status.Success -> {
                    val foxPhoto = viewModel.foxPhoto.value
                    if (foxPhoto != null) {
                        loadPhoto(foxPhoto.image)
                        binding.recentImageCard.likeCheckbox.isChecked = foxPhoto.isFavourite
                    } else {
                        //TODO error toast
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadPhoto(link: String) {
        val request = ImageRequest.Builder(requireContext())
            .data(link)
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
                    // TODO error toast
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
        viewModel.insertToFavourites()
        binding.recentImageCard.likeCheckbox.isChecked = true
        Snackbar.make(
            binding.root,
            getString(R.string.insert_into_db),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun removeFromFavourites() {
        viewModel.deleteFromFavourites()

        Snackbar.make(
            binding.root,
            getString(R.string.delete_from_db),
            Snackbar.LENGTH_LONG
        )
            .setAction(getString(R.string.undo_action)) {
                addToFavourites()
            }
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentImageFragment()
    }
}
