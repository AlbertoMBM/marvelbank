package com.marvel.marvelcharacters

import android.app.Application
import com.marvel.data.di.DataKoinModule
import com.marvel.domain.di.domainModule
import com.marvel.marvelcharacters.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class MarvelCharApp: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI(){
        startKoin {
            androidContext(this@MarvelCharApp)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    DataKoinModule(
                        BuildConfig.BASE_URL, BuildConfig.USER_KEY,
                        BuildConfig.HASH_KEY
                    ).getModule()
                )
            )
        }
    }
}