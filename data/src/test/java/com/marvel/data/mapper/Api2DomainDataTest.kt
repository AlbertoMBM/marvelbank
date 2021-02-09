package com.marvel.data.mapper

import com.marvel.data.entities.*
import com.marvel.domain.entities.*
import org.junit.Assert.assertEquals
import org.junit.Test

class Api2DomainDataTest {

    private val allData = AllData(
        "", "", "", "", "", "",
        ContentData(0, 0, 1200,0 , emptyList())
    )
    private val domainData = DomainData(1200, emptyList())

    private val characterData = CharacterData(
        101732, "Spiderman", "", "", ImageData("", ""),
        "", ProductData(0, "", emptyList(), 0),
        ProductData(0, "", emptyList(), 0),
        ProductData(0, "", emptyList(), 0),
        ProductData(0, "", emptyList(), 0),
        emptyList()
    )
    private val domainCharacter = MarvelCharacter(
        101732, "Spiderman", "", "", Image("", ""),
        "", Product(0, "", emptyList(), 0),
        Product(0, "", emptyList(), 0),
        Product(0, "", emptyList(), 0),
        Product(0, "", emptyList(), 0),
        emptyList()
    )

    private val imageData = ImageData("path", "jpg")
    private val image = Image("path", "jpg")

    private val productData = ProductData(
        12, "", emptyList(), 0
    )
    private val product = Product(
        12, "", emptyList(), 0
    )

    private val productItemData = ProductItemData(
        "resourceURI", "The incredible Spiderman #1", ""
    )
    private val productItem = ProductItem(
        "resourceURI", "The incredible Spiderman #1", ""
    )

    private val characterUrlData = CharacterUrlData(
        "", "url"
    )
    private val characterUrl = CharacterUrl(
        "", "url"
    )

    @Test
    fun toDomainData(){
        assertEquals(domainData, allData.toDomainData())
    }

    @Test
    fun toDomainCharacter(){
        assertEquals(domainCharacter, characterData.toDomainCharacter())
    }

    @Test
    fun toDomainImage(){
        assertEquals(image, imageData.toDomainImage())
    }

    @Test
    fun toDomainProduct(){
        assertEquals(product, productData.toDomainProduct())
    }

    @Test
    fun toDomainProductItem(){
        assertEquals(productItem, productItemData.toDomainProductItem())
    }

    @Test
    fun toCharacterURL(){
        assertEquals(characterUrl, characterUrlData.toCharacterUrl())
    }

}