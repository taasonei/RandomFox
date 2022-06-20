package com.github.taasonei.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.taasonei.databinding.FragmentRecentImageBinding
import com.github.taasonei.extensions.onTouch

class RecentImageFragment : Fragment() {

    private var _binding: FragmentRecentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBox = binding.recentImageCard.likeCheckbox

        val gestureDetector =
            GestureDetector(requireContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    checkBox.isChecked = !checkBox.isChecked
                    return super.onDoubleTap(e)
                }
            })

        binding.recentImageCard.imageCard.onTouch { v, event ->
            gestureDetector.onTouchEvent(event)
        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //TODO add to db
                Toast.makeText(requireContext(), "Added to favourites", Toast.LENGTH_SHORT).show()
            } else {
                //TODO remove from db
                Toast.makeText(requireContext(), "Removed from favourites", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecentImageFragment()
    }
}
