package com.github.taasonei.randomfox.presentation.view

import androidx.recyclerview.widget.DiffUtil
import com.github.taasonei.randomfox.presentation.model.FoxPhoto

class ImageCardDiffUtil : DiffUtil.ItemCallback<FoxPhoto>() {

    override fun areItemsTheSame(oldItem: FoxPhoto, newItem: FoxPhoto): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: FoxPhoto, newItem: FoxPhoto): Boolean {
        return oldItem == newItem
    }

}
