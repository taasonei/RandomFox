package com.github.taasonei.randomfox.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.taasonei.randomfox.recycler.ImageCardAdapter
import com.github.taasonei.randomfox.databinding.FragmentFavouriteListBinding
import com.github.taasonei.randomfox.model.photoList

class FavouritesListFragment : Fragment() {

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!

    private var adapter: ImageCardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageCardAdapter()
        binding.favouritesList.adapter = adapter
        adapter?.submitList(photoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouritesListFragment()
    }
}