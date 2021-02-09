package com.marvel.data.repositories

import arrow.core.Either
import com.marvel.data.datasources.CharacterDataSource
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import com.marvel.domain.repositories.CharacterRepository

class CharacterRepositoryImpl(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository {

    override fun getCharacters(characterId: Long, offset: Int): Either<Failure, DomainData?> {
        return characterDataSource.getCharacters(characterId, offset)
    }
}