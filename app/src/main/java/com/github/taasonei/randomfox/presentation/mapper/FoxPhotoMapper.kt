package com.github.taasonei.randomfox.presentation.mapper

import com.github.taasonei.randomfox.domain.model.DomainFoxPhoto
import com.github.taasonei.randomfox.presentation.model.FoxPhoto

fun FoxPhoto.asDomainFoxPhoto(): DomainFoxPhoto {
    return DomainFoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link,
        isFavourite = this.isFavourite
    )
}

fun DomainFoxPhoto.asFoxPhoto(): FoxPhoto {
    return FoxPhoto(
        id = this.id,
        image = this.image,
        link = this.link,
        isFavourite = this.isFavourite
    )
}

fun List<DomainFoxPhoto>.asFoxPhotoList(): List<FoxPhoto> {
    return map { domainFoxPhoto ->
        FoxPhoto(
            id = domainFoxPhoto.id,
            image = domainFoxPhoto.image,
            link = domainFoxPhoto.link,
            isFavourite = domainFoxPhoto.isFavourite
        )
    }
}
