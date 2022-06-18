package com.github.taasonei.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.taasonei.databinding.ImageCardItemBinding
import com.github.taasonei.model.Photo

class ImageCardAdapter : ListAdapter<Photo, ImageCardViewHolder>(ImageCardDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardViewHolder {
        return ImageCardViewHolder(
            ImageCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}