package com.abcinternational.popdot.net.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Sunita on 07, February, 2019
 * Used to add headers to the REST API calls.
 */
class BaseRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader(
                CONTENT_TYPE,
                APPLICATION_JSON
            )
            .addHeader(
                REST_API_KEY,
                REST_API_VALUE
            )
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }

    companion object {
        private val CONTENT_TYPE = "Content-Type"
        private val APPLICATION_JSON = "application/json; charset=utf-8"
        private val REST_API_VALUE = "4d717e90-7323-423c-b198-47eabe4975a5"
        private val REST_API_KEY = "rest-api-key"
    }
}
