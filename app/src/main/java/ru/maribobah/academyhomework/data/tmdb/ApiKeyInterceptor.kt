package ru.maribobah.academyhomework.data.tmdb

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val httpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_HEADER, API_KEY_VALUE).build()
        val request = originalRequest.newBuilder()
            .url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_HEADER = "api_key"
        private const val API_KEY_VALUE = "ac9bfcf0f2ab823074d839782a86f14d"
    }
}
