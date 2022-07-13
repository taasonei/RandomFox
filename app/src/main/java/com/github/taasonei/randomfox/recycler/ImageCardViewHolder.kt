package com.github.taasonei.randomfox.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.github.taasonei.randomfox.R
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding
import com.github.taasonei.randomfox.model.FoxPhoto

class ImageCardViewHolder(private val binding: ImageCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(foxPhoto: FoxPhoto) {
        binding.likeCheckbox.isChecked = foxPhoto.isFavourite
        binding.foxPhoto.load(foxPhoto.image) {
            error(R.drawable.ic_baseline_broken_image)
            scale(Scale.FILL)
        }
    }
}