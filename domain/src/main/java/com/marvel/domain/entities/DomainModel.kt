package com.marvel.domain.entities

import androidx.annotation.Keep

@Keep
data class DomainData(
    val total: Int,
    val results : List<MarvelCharacter>
)

@Keep
data class MarvelCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Image,
    val resourceUri: String,
    val comics: Product,
    val series: Product,
    val stories: Product,
    val events: Product,
    val urls: List<CharacterUrl>
)

@Keep
data class Image(
    val path: String,
    val extension: String
)

@Keep
data class Product(
    val available: Int,
    val collectionURI: String,
    val items: List<ProductItem>,
    val returned: Int
)

@Keep
data class ProductItem(
    val resourceURI: String,
    val name: String,
    val type: String? = ""
)

@Keep
data class CharacterUrl(
    val type: String,
    val url: String
)