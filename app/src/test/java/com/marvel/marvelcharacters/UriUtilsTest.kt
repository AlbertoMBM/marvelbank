package com.marvel.marvelcharacters

import org.junit.Assert
import org.junit.Test

class UriUtilsTest {

    private val path = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce5a385a2e82"
    private val pathWellFormed = "https://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce5a385a2e82.jpg"

    @Test
    fun getUri(){
        Assert.assertEquals(
            com.marvel.marvelcharacters.utils.getUri(path, "", "jpg"),
            pathWellFormed
        )
    }
}