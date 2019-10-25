package com.abcinternational.popdot.net.interceptors

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Sunita on 07, February, 2019
 * Singleton class that will return the object of HttpLoggingInterceptor.
 */

enum class HttpLoggingInterceptorExtended {
    HTTP_LOGGING_INTERCEPTOR_EXTENDED_INSTANCE;

    private var httpLoggingInterceptor: HttpLoggingInterceptor? = null

    /**
     * This method returns object of HttpLoggingInterceptor
     *
     * @return Object of HttpLoggingInterceptor
     */
    val interceptor: HttpLoggingInterceptor
        get() {
            if (httpLoggingInterceptor == null) {
                httpLoggingInterceptor = HttpLoggingInterceptor()
            }
            return httpLoggingInterceptor!!
        }
}
