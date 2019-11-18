package com.coppermobile.android.egowall.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coppermobile.android.egowall.di.Injection
import com.coppermobile.android.egowall.viewmodels.EmailPhoneViewModel
import com.coppermobile.android.egowall.viewmodels.LoginViewModel
import com.coppermobile.android.egowall.viewmodels.SignupViewModel

class ViewModelFactory private constructor() : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        ViewModelFactory()
                }
            }
            return INSTANCE!!
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> SignupViewModel(Injection.provideValidateFreeTrialRepository()) as T
            modelClass.isAssignableFrom(EmailPhoneViewModel::class.java) -> EmailPhoneViewModel() as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(Injection.provideEmailLoginRepository()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}