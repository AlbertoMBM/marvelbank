package com.marvel.marvelcharacters.di

import com.marvel.marvelcharacters.ui.character.CharacterViewModel
import com.marvel.marvelcharacters.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    viewModel<HomeViewModel> {
        HomeViewModel(characterUseCase = get())
    }

    viewModel<CharacterViewModel> {
        CharacterViewModel(characterUseCase = get())
    }
}