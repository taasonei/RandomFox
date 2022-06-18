package com.github.taasonei.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.taasonei.model.Photo

class ImageCardDiffUtil : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.res == newItem.res
    }
}