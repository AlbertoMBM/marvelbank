package com.marvel.domain.repositories

import arrow.core.Either
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure

interface CharacterRepository {

    fun getCharacters(characterId: Long, offset: Int): Either<Failure, DomainData?>

}