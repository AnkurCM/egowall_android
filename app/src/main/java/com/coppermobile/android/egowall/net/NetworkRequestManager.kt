package com.coppermobile.android.egowall.net

import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Created by Sunita on 07, February, 2019
 * Network request manager to define http requests.
 */
////TODO:Currently not in use.
interface NetworkRequestManager {
    /**
     * Method to make the post request
     *
     * @param url    api endpoint to be consumed
     * @param object body of the post request
     * @return an object of Java Object type so as to get a generic object which will be casted to desired type later
     */
    @POST
    fun postRequest(@Header("access-token") accessToken: String?, @Url url: String?, @Body `object`: Any?): Single<String>

    /**
     * Method to make the get request
     *
     * @param url api endpoint to be consumed
     * @return an object of Java Object type so as to get a generic object which will be casted to desired type later
     */
    @GET
    fun getRequest(@Header("access-token") accessToken: String?, @Url url: String?): Single<String>

    @PUT
    fun putRequest(@Url url: String, @Body `object`: Any): Single<String>

    @Multipart
    @POST
    fun uploadFile(@Header("access-token") accessToken: String?, @Url url: String?, @Part image: MultipartBody.Part?): Single<String>
}
