package com.marvel.marvelcharacters.ui.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import com.marvel.domain.entities.DomainData
import com.marvel.domain.test.mock
import com.marvel.domain.usecases.CharacterUseCase
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val characterUseCase: CharacterUseCase = mock()
    private val navController : NavController = mock()
    private lateinit var homeViewModel : HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(characterUseCase)
    }

    @Test
    fun getNextCharacters() {
        homeViewModel.getNextCharacters(0)

    }

    @Test
    fun navigateToCharacter() {
        homeViewModel.navigateToCharacter(navController, 0L)
    }
}