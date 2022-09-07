package com.github.taasonei.randomfox.data.mapper

import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import com.github.taasonei.randomfox.data.source.model.local.LastFoxPhoto
import com.github.taasonei.randomfox.data.source.model.remote.NetworkFoxPhoto
import com.github.taasonei.randomfox.domain.model.FoxPhoto

fun NetworkFoxPhoto.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        image = this.image ?: "",
        link = this.link ?: ""
    )
}

fun LastFoxPhoto.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link,
        isFavourite = this.isFavourite
    )
}

fun List<FavouriteFoxPhoto>.asListFoxPhoto(): List<FoxPhoto> {
    return map { favouriteFoxPhoto ->
        FoxPhoto(
            id = favouriteFoxPhoto.id,
            image = favouriteFoxPhoto.image,
            link = favouriteFoxPhoto.link,
            isFavourite = true
        )
    }
}

fun FoxPhoto.asFavouriteFoxPhoto(): FavouriteFoxPhoto {
    return FavouriteFoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link
    )
}