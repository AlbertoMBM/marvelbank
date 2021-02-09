package com.marvel.data.mapper

import com.marvel.data.entities.*
import com.marvel.domain.entities.*

fun AllData.toDomainData(): DomainData {
    return DomainData(
        this.data.total,
        this.data.results.map {
            it.toDomainCharacter()
        }
    )
}

fun CharacterData.toDomainCharacter(): MarvelCharacter {
    return MarvelCharacter(
        this.id,
        this.name,
        this.description,
        this.modified,
        this.thumbnail.toDomainImage(),
        this.resourceUri ?: "",
        this.comics?.toDomainProduct() ?: Product(0, "", emptyList(), 0),
        this.series?.toDomainProduct() ?: Product(0, "", emptyList(), 0),
        this.stories?.toDomainProduct() ?: Product(0, "", emptyList(), 0),
        this.events?.toDomainProduct() ?: Product(0, "", emptyList(), 0),
        this.urls.map {
            it.toCharacterUrl()
        }
    )
}

fun ImageData.toDomainImage(): Image {
    return Image(
        this.path, this.extension
    )
}

fun ProductData.toDomainProduct(): Product {
    return Product(
        this.available,
        this.collectionURI,
        this.items.map {
            it.toDomainProductItem()
        },
        this.returned
    )
}

fun ProductItemData.toDomainProductItem(): ProductItem {
    return ProductItem(
        this.resourceURI, this.name, this.type ?: ""
    )
}

fun CharacterUrlData.toCharacterUrl(): CharacterUrl {
    return CharacterUrl(
        this.type, this.url
    )
}