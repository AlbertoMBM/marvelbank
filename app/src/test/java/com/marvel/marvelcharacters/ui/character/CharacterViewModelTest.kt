package com.marvel.marvelcharacters.ui.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvel.domain.test.mock
import com.marvel.domain.usecases.CharacterUseCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class CharacterViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val characterUseCase: CharacterUseCase = mock()
    private val characterViewModel = CharacterViewModel(characterUseCase)

    @Test
    fun getCharacterData() {
        characterViewModel.getCharacterData(0L)
    }
}