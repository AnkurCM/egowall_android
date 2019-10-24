package com.coppermobile.android.egowall.repositories

import androidx.lifecycle.LiveData

class SignupRepository private constructor(private val signupDataSource: SignupDataSource) {

    companion object {

        private var INSTANCE: SignupRepository? = null

        fun getInstance(signupDataSource: SignupDataSource): SignupRepository {
            if (INSTANCE == null) {
                synchronized(SignupRepository::class.java) {
                    INSTANCE =
                        SignupRepository(registerDataSource)
                }
            }
            return INSTANCE!!
        }
    }

    fun getRegisterResponse(signupRequest: RegistrationRequest): LiveData<RegistrationResponse> {
        return signupDataSource.getRegisterResponse(signupRequest)
    }
}