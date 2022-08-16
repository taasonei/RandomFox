package com.github.taasonei.randomfox.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.github.taasonei.randomfox.R
import com.github.taasonei.randomfox.databinding.FragmentFavouriteListBinding
import com.github.taasonei.randomfox.ui.viewmodels.FavouritesListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FavouritesListFragment : Fragment() {

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesListViewModel by viewModels()

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
        adapter = ImageCardAdapter(viewModel)
        adapter?.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.favouritesList.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.listFoxPhoto.collect() { list ->
                adapter?.submitList(list)
            }
        }

        viewModel.foxPhoto.observe(viewLifecycleOwner) { foxPhoto ->
            if (foxPhoto != null) {
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    getString(R.string.delete_from_db),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.undo_action)) {
                    viewModel.insertToFavourites(foxPhoto)
                }
                    .show()
            }
        }

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