package com.coppermobile.android.egowall.data.requests

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("userEmail")
    var userEmail: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("format")
    var format: String? = null
}