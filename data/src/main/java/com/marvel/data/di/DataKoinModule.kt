package com.marvel.data.di

import com.marvel.data.datasources.CharacterDataSource
import com.marvel.data.network.ApiServices
import com.marvel.data.network.NetworkUtils
import com.marvel.data.network.interceptor.KeyInterceptor
import com.marvel.data.parser.MoshiJsonParser
import com.marvel.data.repositories.CharacterRepositoryImpl
import com.marvel.domain.repositories.CharacterRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class DataKoinModule(
    private val baseUrl: String, private val apiKey: String,
    private val hash: String
) {

    fun getModule(): Module = module {

        single<CharacterRepository> {
            CharacterRepositoryImpl(characterDataSource = get())
        }

        factory<CharacterDataSource> {
            CharacterDataSource(apiServices = get(), networkUtils = get())
        }

        single<ApiServices> {
            createApiClient(okHttpClient = createOkHttpClient(), baseUrl = baseUrl)
        }

        single {
            createOkHttpClient()
        }

        single<KeyInterceptor> {
            KeyInterceptor(apiKey, hash)
        }

        single {
            NetworkUtils(androidContext())
        }
    }

    private inline fun <reified T> createApiClient(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(MoshiJsonParser.moshi))
            .client(okHttpClient)
            .build()
        return retrofit.create(T::class.java)
    }

    private fun createOkHttpClient(
    ): OkHttpClient {
        var loggin = HttpLoggingInterceptor()
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(loggin)
            .addInterceptor(KeyInterceptor(apiKey, hash))
            .build()
    }
}