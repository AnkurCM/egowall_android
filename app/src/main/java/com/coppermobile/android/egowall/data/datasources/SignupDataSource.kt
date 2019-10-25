package com.coppermobile.android.egowall.data.datasources

import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import com.coppermobile.android.egowall.net.ApiClient
import io.reactivex.Observable

class SignupDataSource private constructor() {

    private object Holder {
        val INSTANCE = SignupDataSource()
    }

    companion object {
        val instance: SignupDataSource by lazy { Holder.INSTANCE }
    }

    fun getSignupResponse(signupRequest: SignupRequest): Observable<SignupResponse> {
        return ApiClient.getApiClient().instance!!.userRegistration(signupRequest)
    }
}