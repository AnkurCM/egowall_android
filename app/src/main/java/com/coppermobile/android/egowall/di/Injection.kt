package com.coppermobile.android.egowall.di

import com.coppermobile.android.egowall.repositories.SignupRepository

object Injection {

    fun provideValidateFreeTrialRepository(): SignupRepository {
        return SignupRepository.getInstance()
    }
}