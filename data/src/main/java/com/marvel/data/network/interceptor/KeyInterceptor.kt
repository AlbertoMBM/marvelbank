package com.marvel.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor(private val userKey: String, private val hashKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter("ts", "1")
            .addQueryParameter("apikey", userKey)
            .addQueryParameter("hash",hashKey)
            .build()

        request = request.newBuilder().url(newUrl).build()
        return chain.proceed(request)
    }

}