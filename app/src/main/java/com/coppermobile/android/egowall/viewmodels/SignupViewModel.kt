package com.coppermobile.android.egowall.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coppermobile.android.egowall.repositories.SignupRepository

class SignupViewModel (private var signupRepository: SignupRepository) : ViewModel()  {

    fun getRegistrationResponse(signupRequest: SignupRequest): LiveData<SignupResponse> {
        return signupRepository.getRegisterResponse(signupRequest)
    }
}