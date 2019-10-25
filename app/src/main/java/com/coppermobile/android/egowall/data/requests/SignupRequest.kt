package com.coppermobile.android.egowall.data.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Sunita on 15, February, 2019
 * This class is request class for changePassword password.
 */
class SignupRequest {

    @SerializedName("password")
    var password: String? = null

    @SerializedName("new_password")
    var newPassword: String? = null
}