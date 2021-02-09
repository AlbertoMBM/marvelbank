package com.marvel.domain.usecases

import arrow.core.Either
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import com.marvel.domain.repositories.CharacterRepository
import com.marvel.domain.test.eq
import com.marvel.domain.test.mock
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class CharacterUseCaseTest {

    private val characterRepository: CharacterRepository = mock()
    private val characterUseCase = CharacterUseCase(characterRepository)
    private var characterId: Long = 0L
    private var offset: Int = 0

    fun getDomainData(): DomainData =
        DomainData(0, emptyList())

    @Test
    fun run() {
        val repositoryResult: Either<Failure, DomainData?> = Either.right(getDomainData())
        Mockito.`when`(characterRepository.getCharacters(characterId, offset)).thenReturn(
            Either.right(getDomainData()))
        val result: Either<Failure, DomainData?> = characterUseCase.run(
            CharacterUseCase.Params.params(characterId, offset)
        )
        assertEquals(repositoryResult, result)
    }

    @Test
    fun runKo(){
        val result: Either<Failure, DomainData?> = characterUseCase.run()
        assertEquals(eq(result), eq(Either.left(Failure.GenericFailure())))
    }
}