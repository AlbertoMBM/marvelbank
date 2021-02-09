package com.marvel.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import arrow.core.Either
import com.marvel.domain.entities.Failure
import com.marvel.domain.test.eq
import com.marvel.domain.test.mock
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import retrofit2.Response

class NetworkUtilsTest {

    private val context: Context = mock()
    private val networkUtils = NetworkUtils(context)
    private val connectivityManager : ConnectivityManager = mock()
    private val network : Network = mock()
    private val networkCapabilities : NetworkCapabilities = mock()
    private val response : Response<Boolean> = mock()

    @Test
    fun isInternetAvailable() {
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
            connectivityManager
        )
        Mockito.`when`(connectivityManager.activeNetwork).thenReturn(
            network
        )
        Mockito.`when`(connectivityManager.getNetworkCapabilities(network)).thenReturn(
            networkCapabilities
        )
        Mockito.`when`(networkUtils.isInternetAvailable()).thenReturn(
            true
        )
        assertEquals(true, networkUtils.isInternetAvailable())
    }

    @Test
    fun manageError() {
        Mockito.`when`(response.code()).thenReturn(
            408
        )
        val value = networkUtils.manageError<Failure,Boolean>(response)
        assertEquals(value, Either.left(Failure.Timeout))
        Mockito.`when`(response.code()).thenReturn(
            500
        )
        val value2 = networkUtils.manageError<Failure,Boolean>(response)
        assertSame(value2::class, Either.left(Failure.ServerError(response.message()))::class)
        Mockito.`when`(response.code()).thenReturn(
            401
        )
        val value3 = networkUtils.manageError<Failure,Boolean>(response)
        assertSame(value3::class, Either.left(Failure.GenericFailure("401", ""))::class)
    }
}