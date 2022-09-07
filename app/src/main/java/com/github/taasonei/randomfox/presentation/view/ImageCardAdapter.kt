package com.github.taasonei.randomfox.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.taasonei.randomfox.presentation.model.FoxPhoto
import com.github.taasonei.randomfox.databinding.ImageCardItemBinding
import com.github.taasonei.randomfox.presentation.viewmodels.FavouritesViewModel

class ImageCardAdapter(private val viewModel: FavouritesViewModel) : ListAdapter<FoxPhoto, ImageCardViewHolder>(
    ImageCardDiffUtil()
) {
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