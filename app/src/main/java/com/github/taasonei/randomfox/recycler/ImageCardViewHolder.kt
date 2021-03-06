package com.github.taasonei.randomfox.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.taasonei.randomfox.R
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.viewmodel.FavouritesListViewModel

class ImageCardViewHolder(private val binding: ImageCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: FavouritesListViewModel, foxPhoto: FoxPhoto) {
        binding.likeCheckbox.isChecked = foxPhoto.isFavourite
        binding.likeCheckbox.setOnClickListener {
            viewModel.deleteFromFavourites(foxPhoto)
        }
        binding.foxPhoto.load(foxPhoto.image) {
            error(R.drawable.ic_baseline_broken_image)
        }
    }
}
