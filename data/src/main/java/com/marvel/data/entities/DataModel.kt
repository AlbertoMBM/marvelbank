package com.marvel.data.entities

data class AllData(
    val code: String,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHtml: String?,
    val etag: String,
    val data: ContentData
)

data class ContentData(
    val offset : Int,
    val limit : Int,
    val total: Int,
    val count : Int,
    val results : List<CharacterData>
)

data class CharacterData(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ImageData,
    val resourceUri: String?,
    val comics: ProductData?,
    val series: ProductData?,
    val stories: ProductData?,
    val events: ProductData?,
    val urls: List<CharacterUrlData>
)

data class ImageData(
    val path: String,
    val extension: String
)

data class ProductData(
    val available: Int,
    val collectionURI: String,
    val items: List<ProductItemData>,
    val returned: Int
)

data class ProductItemData(
    val resourceURI: String,
    val name: String,
    val type: String? = ""
)

data class CharacterUrlData(
    val type: String,
    val url: String
)