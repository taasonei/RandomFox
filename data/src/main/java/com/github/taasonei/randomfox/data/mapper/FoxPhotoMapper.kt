package com.github.taasonei.randomfox.data.mapper

import com.github.taasonei.randomfox.data.source.model.local.FavouriteFoxPhoto
import com.github.taasonei.randomfox.data.source.model.remote.NetworkFoxPhoto
import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto

fun NetworkFoxPhoto.asFoxPhoto(): DomainFoxPhoto {
    return DomainFoxPhoto(
        image = this.image ?: "",
        link = this.link ?: ""
    )
}

fun List<FavouriteFoxPhoto>.asListFoxPhoto(): List<DomainFoxPhoto> {
    return map { favouriteFoxPhoto ->
        DomainFoxPhoto(
            id = favouriteFoxPhoto.id,
            image = favouriteFoxPhoto.image,
            link = favouriteFoxPhoto.link,
            isFavourite = true
        )
    }
}

fun DomainFoxPhoto.asFavouriteFoxPhoto(): FavouriteFoxPhoto {
    return FavouriteFoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link
    )
}
