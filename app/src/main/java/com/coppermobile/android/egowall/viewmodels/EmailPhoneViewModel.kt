package com.coppermobile.android.egowall.viewmodels

import androidx.lifecycle.ViewModel
import com.coppermobile.android.egowall.utils.Helpers.isEmailValid
import com.coppermobile.android.egowall.utils.Helpers.isPhone
import com.coppermobile.android.egowall.utils.Helpers.isPhoneValid

class EmailPhoneViewModel() : ViewModel() {
    fun verifyEmailOrPhone(entity: String): Boolean? {
        if (isPhone(entity)!!) {
            return isPhoneValid(entity)
        } else {
            return isEmailValid(entity)
        }
    }

    fun handleInput(entity: String): Boolean? {
        return verifyEmailOrPhone(entity)
    }


}