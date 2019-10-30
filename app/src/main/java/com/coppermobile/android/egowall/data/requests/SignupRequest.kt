package com.coppermobile.android.egowall.data.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Sunita on 15, February, 2019
 * This class is request class for changePassword password.
 */
class SignupRequest {

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("lastName")
    var lastName: String? = null

    @SerializedName("userEmail")
    var userEmail: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("format")
    var format: String? = null
}