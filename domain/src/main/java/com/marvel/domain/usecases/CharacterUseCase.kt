package com.marvel.domain.usecases

import arrow.core.Either
import arrow.core.Left
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import com.marvel.domain.repositories.CharacterRepository

class CharacterUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<DomainData?, CharacterUseCase.Params>() {

    override fun run(params: Params?): Either<Failure, DomainData?> {
        return when (params) {
            is Params -> {
                characterRepository.getCharacters(
                    params.characterId, params.offset
                )
            }
            else -> {
                Left(Failure.GenericFailure())
            }
        }
    }

    class Params private constructor(
        val characterId: Long,
        val offset: Int
    ) {
        companion object {
            fun params(characterId: Long, offset: Int = 0): Params {
                return Params(characterId, offset)
            }
        }
    }
}