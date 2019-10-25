package com.coppermobile.android.egowall.net

import com.abcinternational.popdot.net.interceptors.BaseRequestInterceptor
import com.abcinternational.popdot.net.interceptors.HttpLoggingInterceptorExtended
import com.coppermobile.android.egowall.BuildConfig
import com.coppermobile.android.egowall.data.URLs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder


/**
 * Created by Sunita on 07, February, 2019
 * Rest client instance to access api call in Api interface
 */
class ApiClient {

    private var apiCallback: ApiInterface? = null

    /**
     * Method to to get api client instance
     *
     * @return ApiInterface instance
     */
    val instance: ApiInterface?
        get() {
            try {
                if (apiCallback == null) {

                    val httpClient = OkHttpClient.Builder()
                    httpClient.connectTimeout(BuildConfig.TIMEOUT_IN_MIN, TimeUnit.MINUTES)
                    httpClient.readTimeout(BuildConfig.TIMEOUT_IN_MIN, TimeUnit.MINUTES)
                    val loggingInterceptor = HttpLoggingInterceptorExtended.HTTP_LOGGING_INTERCEPTOR_EXTENDED_INSTANCE.interceptor
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addNetworkInterceptor(loggingInterceptor)
                    httpClient.retryOnConnectionFailure(true)
                    httpClient.addInterceptor(BaseRequestInterceptor())
                    val gson = GsonBuilder()
                        .setLenient()
                        .create()
                    val client = Retrofit.Builder()
                        .baseUrl(URLs.BASE_URL)
                        .client(httpClient.build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                    apiCallback = client.create(ApiInterface::class.java)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return apiCallback
        }

    companion object {

        private var apiClient: ApiClient? = null

        fun getApiClient(): ApiClient {
            if (apiClient == null) {
                apiClient = ApiClient()
            }
            return apiClient as ApiClient
        }
    }
}
