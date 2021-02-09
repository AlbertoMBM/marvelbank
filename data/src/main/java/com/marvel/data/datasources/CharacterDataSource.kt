package com.marvel.data.datasources

import arrow.core.Either
import com.marvel.data.entities.AllData
import com.marvel.data.mapper.toDomainData
import com.marvel.data.network.ApiServices
import com.marvel.data.network.NetworkUtils
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import retrofit2.Response

class CharacterDataSource(
    private val apiServices: ApiServices,
    private val networkUtils: NetworkUtils
) {

    fun getCharacters(characterId: Long, offset: Int) : Either<Failure, DomainData?> {
        return if (networkUtils.isInternetAvailable()){
            val response = getResponse(characterId, offset)
            if (response.isSuccessful){
                Either.right(response.body()?.toDomainData())
            }else{
                networkUtils.manageError(response)
            }
        }else{
            Either.left(Failure.NoInternetConnection)
        }
    }

    private fun getResponse(characterId: Long, offset: Int): Response<AllData>{
        return if (characterId != 0L) {
            apiServices.getCharacter(characterId).execute()
        }else{
            apiServices.getCharacters(offset).execute()
        }
    }
}