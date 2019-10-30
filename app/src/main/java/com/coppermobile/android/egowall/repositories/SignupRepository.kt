package com.coppermobile.android.egowall.repositories

import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import com.coppermobile.android.egowall.net.ApiClient
import io.reactivex.Observable
import okhttp3.MultipartBody


class SignupRepository private constructor() {

    companion object {

        private var INSTANCE: SignupRepository? = null

        fun getInstance(): SignupRepository {
            if (INSTANCE == null) {
                synchronized(SignupRepository::class.java) {
                    INSTANCE =
                        SignupRepository()
                }
            }
            return INSTANCE!!
        }
    }

    fun getRegisterResponse(signupRequest: SignupRequest): Observable<SignupResponse> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("userEmail", signupRequest.userEmail)
            .addFormDataPart("password", signupRequest.password)
            .addFormDataPart("format", "json")
            .build()
        return ApiClient.getApiClient().instance!!.userRegistration(requestBody)
    }
}