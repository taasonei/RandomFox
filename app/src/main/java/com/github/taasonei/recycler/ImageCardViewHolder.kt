package com.github.taasonei.recycler

import androidx.recyclerview.widget.RecyclerView
import com.github.taasonei.databinding.ImageCardItemBinding
import com.github.taasonei.model.Photo

class ImageCardViewHolder(private val binding: ImageCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo) {
        binding.foxPhoto.setImageResource(photo.res)
    }
}