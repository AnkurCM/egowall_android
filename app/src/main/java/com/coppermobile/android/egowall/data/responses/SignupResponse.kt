package com.coppermobile.android.egowall.data.responses

import com.google.gson.annotations.SerializedName

//class SignupResponse : BaseResponse() {
class SignupResponse {

    @SerializedName("data")
    var data: List<RegistrationDataResponse>? = null


    class RegistrationDataResponse {

        @SerializedName("name")
        val name: String? = null

        @SerializedName("email")
        val email: String? = null

        @SerializedName("language")
        val language: String? = null

        @SerializedName("country")
        val country: String? = null

        @SerializedName("device_type")
        val deviceType: String? = null

        @SerializedName("device_token")
        val deviceToken: String? = null

        @SerializedName("device_id")
        val deviceId: String? = null

        @SerializedName("activation_code")
        val activationCode: String? = null

        @SerializedName("user_status")
        val userStatus: Int? = null

    }
}

