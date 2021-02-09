package com.marvel.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import arrow.core.Either
import com.marvel.domain.entities.DomainData
import com.marvel.domain.entities.Failure
import retrofit2.Response

class NetworkUtils(private val context: Context){
    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun <T, U>manageError(response: Response<U>) : Either<Failure, T?> {
        return when(response.code()){
            408 -> Either.left(Failure.Timeout)
            500 -> Either.left(Failure.ServerError(response.message()))
            else -> Either.left(Failure.GenericFailure(response.code().toString(), response.message()))
        }
    }
}
