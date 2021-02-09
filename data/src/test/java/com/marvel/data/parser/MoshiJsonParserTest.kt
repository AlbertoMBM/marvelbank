package com.marvel.data.parser

import com.marvel.data.entities.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MoshiJsonParserTest {

    private val moshiJsonParser = MoshiJsonParser()

    @Before
    fun setUp() {
    }

    @Test
    fun fromJson() {
        val json = """
            {
                "available" : 2,
                "collectionURI" : "http://gateway.marvel.com/v1/public/characters/1010836/series",
                "items" : [
                    {
                        "resourceURI" : "http://gateway.marvel.com/v1/public/series/27824",
                        "name" : "Future Foundation (2019 - Present)"
                    },
                    {
                        "resourceURI" : "http://gateway.marvel.com/v1/public/series/3077",
                        "name" : "Iron Man and Power Pack (2007 - 2008)"
                    }
                ],
                "returned" : 0
            }
        """.trimIndent()

        // When
        val productData: ProductData = moshiJsonParser.fromJson(json, ProductData::class.java)!!

        // Then
        val expectedProductData = ProductData(
            2,
            "http://gateway.marvel.com/v1/public/characters/1010836/series",
            listOf(
                ProductItemData(
                    "http://gateway.marvel.com/v1/public/series/27824",
                    "Future Foundation (2019 - Present)"
                ),
                ProductItemData(
                    "http://gateway.marvel.com/v1/public/series/3077",
                    "Iron Man and Power Pack (2007 - 2008)"
                )
            )
        ,0)
        assertEquals(productData, expectedProductData)
    }

    @Test
    fun toJson() {
        // Given
        val productData = ProductData(
            2,
            "http://gateway.marvel.com/v1/public/characters/1010836/series",
            listOf(
                ProductItemData(
                    "http://gateway.marvel.com/v1/public/series/27824",
                    "Future Foundation (2019 - Present)",
                    ""
                ),
                ProductItemData(
                    "http://gateway.marvel.com/v1/public/series/3077",
                    "Iron Man and Power Pack (2007 - 2008)",
                    ""
                )
            )
            ,0)

        // When
        val json: String = moshiJsonParser.toJson(productData)

        // Then
        val expectedJson = """
            {
                "available" : 2,
                "collectionURI" : "http://gateway.marvel.com/v1/public/characters/1010836/series",
                "items" : [
                    {
                        "resourceURI" : "http://gateway.marvel.com/v1/public/series/27824",
                        "name" : "Future Foundation (2019 - Present)",
                        "type" : ""
                    },
                    {
                        "resourceURI" : "http://gateway.marvel.com/v1/public/series/3077",
                        "name" : "Iron Man and Power Pack (2007 - 2008)",
                        "type" : ""
                    }
                ],
                "returned" : 0
            }
        """.trimIndent()
        val newExpectedJson = expectedJson.replace(Regex("\n *"), "")
            .replace(Regex(": "), ":")
            .replace(Regex(" :"), ":")

        assertEquals(json, newExpectedJson)
    }
}