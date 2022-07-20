package com.github.taasonei.randomfox.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.taasonei.randomfox.model.FoxPhoto
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding
import com.github.taasonei.randomfox.viewmodel.FavouritesListViewModel

class ImageCardAdapter(private val viewModel: FavouritesListViewModel) : ListAdapter<FoxPhoto, ImageCardViewHolder>(ImageCardDiffUtil()) {
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
        holder.bind(viewModel, getItem(position))
    }
}