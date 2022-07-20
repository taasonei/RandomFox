package com.github.taasonei.randomfox.recycler

import androidx.recyclerview.widget.DiffUtil
import com.github.taasonei.randomfox.model.FoxPhoto

class ImageCardDiffUtil : DiffUtil.ItemCallback<FoxPhoto>() {
    override fun areItemsTheSame(oldItem: FoxPhoto, newItem: FoxPhoto): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: FoxPhoto, newItem: FoxPhoto): Boolean {
        return oldItem == newItem
    }
}