package com.marvel.domain.di

import com.marvel.domain.usecases.CharacterUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {

    factory<CharacterUseCase> {
        CharacterUseCase(characterRepository = get())
    }

}