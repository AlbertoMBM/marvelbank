package com.marvel.data.network

import com.marvel.data.entities.AllData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    companion object {
        const val CHARACTERS = "characters"
        const val CHARACTER = "characters/{character}"
    }

    @GET(CHARACTERS)
    fun getCharacters(
        @Query("offset") offset: Int
    ): Call<AllData>

    @GET(CHARACTER)
    fun getCharacter(
        @Path("character") id: Long
    ): Call<AllData>

}