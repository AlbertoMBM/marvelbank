package com.marvel.data.repositories

import arrow.core.Either
import com.marvel.data.datasources.CharacterDataSource
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import com.marvel.domain.test.mock
import com.marvel.domain.usecases.CharacterUseCase
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class CharacterRepositoryImplTest {

    private val characterDataSource : CharacterDataSource = mock()
    private val characterRepositoryImpl = CharacterRepositoryImpl(characterDataSource)

    fun getDomainData(): DomainData =
        DomainData(0, emptyList())

    @Before
    fun setUp() {
    }

    @Test
    fun getCharacters() {
        val dataSourceResult: Either<Failure, DomainData?> = Either.right(getDomainData())
        Mockito.`when`(characterDataSource.getCharacters(0L, 0)).thenReturn(
            Either.right(getDomainData()))
        val result: Either<Failure, DomainData?> = characterRepositoryImpl.getCharacters(
            0L, 0
        )
        assertEquals(dataSourceResult, result)
    }
}