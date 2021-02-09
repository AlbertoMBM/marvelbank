package com.marvel.domain.entities

sealed class Failure(val message: String? = null) {
    class GenericFailure(val code: String? = "0", message: String? = null) : Failure(message)
    class ServerError(msg: String?) : Failure(msg) // 500
    object Timeout : Failure() // 408
    object NoInternetConnection : Failure()
}