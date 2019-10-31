package com.coppermobile.android.egowall.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginResponse {

    @SerializedName("isError")
    @Expose
    var isError: Boolean? = null
    @SerializedName("hasData")
    @Expose
    var hasData: Boolean? = null
    @SerializedName("userID")
    @Expose
    var userID: String? = null
    @SerializedName("userName")
    @Expose
    var userName: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null

    @SerializedName("errorCat")
    @Expose
    var errorCat: Int? = null
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null
    @SerializedName("silentError")
    @Expose
    var silentError: Boolean? = null

}

