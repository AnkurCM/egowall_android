package com.coppermobile.android.egowall.repositories

import androidx.lifecycle.LiveData
import com.coppermobile.android.egowall.data.datasources.SignupDataSource
import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse

class SignupRepository private constructor(private val signupDataSource: SignupDataSource) {

    companion object {

        private var INSTANCE: SignupRepository? = null

        fun getInstance(signupDataSource: SignupDataSource): SignupRepository {
            if (INSTANCE == null) {
                synchronized(SignupRepository::class.java) {
                    INSTANCE =
                        SignupRepository(signupDataSource)
                }
            }
            return INSTANCE!!
        }
    }

    fun getRegisterResponse(signupRequest: SignupRequest): LiveData<SignupResponse> {
        return signupDataSource.getSignupResponse(signupRequest)
    }
}