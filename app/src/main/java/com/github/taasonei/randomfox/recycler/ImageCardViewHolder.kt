package com.github.taasonei.randomfox.recycler

import androidx.recyclerview.widget.RecyclerView
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding
import com.github.taasonei.randomfox.model.Photo

class ImageCardViewHolder(private val binding: ImageCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo) {
        binding.foxPhoto.setImageResource(photo.res)
    }
}