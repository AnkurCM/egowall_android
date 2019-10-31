package com.coppermobile.android.egowall.repositories

import com.coppermobile.android.egowall.data.requests.LoginRequest
import com.coppermobile.android.egowall.data.responses.LoginResponse
import com.coppermobile.android.egowall.net.ApiClient
import io.reactivex.Observable
import okhttp3.MultipartBody

class LoginRepository private constructor() {

    companion object {

        private var INSTANCE: LoginRepository? = null

        fun getInstance(): LoginRepository {
            if (INSTANCE == null) {
                synchronized(LoginRepository::class.java) {
                    INSTANCE =
                        LoginRepository()
                }
            }
            return INSTANCE!!
        }
    }

    fun getLoginResponse(loginRequest: LoginRequest): Observable<LoginResponse> {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("userEmail", loginRequest.userEmail)
            .addFormDataPart("password", loginRequest.password)
            .addFormDataPart("format", "json")
            .build()
        return ApiClient.getApiClient().instance!!.userLogin(requestBody)
    }
}