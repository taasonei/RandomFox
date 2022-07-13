package com.github.taasonei.randomfox.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding

class ImageCardAdapter : ListAdapter<FoxPhoto, ImageCardViewHolder>(ImageCardDiffUtil()) {
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