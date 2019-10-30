package com.coppermobile.android.egowall.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//class SignupResponse : BaseResponse() {
//class SignupResponse {
//
//    @SerializedName("isError")
//    val isError: Int? = null
//
//    @SerializedName("errorCat")
//    val errorCat: Int? = null
//
//    @SerializedName("errorMessage")
//    val errorMessage: String? = null
//
//    @SerializedName("silentError")
//    val silentError: Int? = null
//
//    @SerializedName("hasData")
//    val hasData: Int? = null
//
//
//}
class SignupResponse {
    @SerializedName("errorCat")
    val errorCat: Int? = null
    @SerializedName("isError")
    @Expose
    var isError: Boolean? = null
    @SerializedName("errorMessage")
    @Expose
    var errorMessage: ErrorMessage? = null
    @SerializedName("silentError")
    val silentError: Boolean? = null
    @SerializedName("hasData")
    @Expose
    var hasData: Boolean? = null
    @SerializedName("userID")
    @Expose
    var userID: String? = null
    @SerializedName("defaultProfID")
    @Expose
    var defaultProfID: String? = null
    @SerializedName("defaultProfUpdResp")
    @Expose
    var defaultProfUpdResp: String? = null
    @SerializedName("emailInviteResp")
    @Expose
    var emailInviteResp: EmailInviteResp? = null
    @SerializedName("eventResourceGetResp")
    @Expose
    var eventResourceGetResp: EventResourceGetResp? = null
    @SerializedName("eventResourceSetResp")
    @Expose
    var eventResourceSetResp: EventResourceSetResp? = null
    @SerializedName("containerIdArr")
    @Expose
    var containerIdArr: ContainerIdArr? = null
    @SerializedName("profileContSetResp")
    @Expose
    var profileContSetResp: String? = null
    @SerializedName("rewardUpdRespError")
    @Expose
    var rewardUpdRespError: String? = null
    @SerializedName("rewardPts")
    @Expose
    var rewardPts: Int? = null
    @SerializedName("rewardSummary")
    @Expose
    var rewardSummary: List<RewardSummary>? = null

}

class ErrorMessage {

    @SerializedName("msg")
    @Expose
    var msg: String? = null

}

class _1 {

    @SerializedName("1")
    @Expose
    var _1: List<String>? = null
    @SerializedName("2")
    @Expose
    var _2: List<String>? = null
    @SerializedName("3")
    @Expose
    var _3: List<String>? = null

}

class _3 {

    @SerializedName("834")
    @Expose
    var _834: String? = null
    @SerializedName("835")
    @Expose
    var _835: String? = null
    @SerializedName("836")
    @Expose
    var _836: String? = null
    @SerializedName("837")
    @Expose
    var _837: String? = null
    @SerializedName("838")
    @Expose
    var _838: String? = null

}

class RewardSummary {

    @SerializedName("eventID")
    @Expose
    var eventID: String? = null
    @SerializedName("eventName")
    @Expose
    var eventName: String? = null
    @SerializedName("subeventID")
    @Expose
    var subeventID: String? = null
    @SerializedName("subeventName")
    @Expose
    var subeventName: String? = null
    @SerializedName("feedTypeID")
    @Expose
    var feedTypeID: String? = null
    @SerializedName("pointsRewarded")
    @Expose
    var pointsRewarded: String? = null

}

class EventResourceSetResp {

    @SerializedName("1")
    @Expose
    var _1: _1? = null
    @SerializedName("3")
    @Expose
    var _3: _3? = null
    @SerializedName("contUpdateResp")
    @Expose
    var contUpdateResp: String? = null

}

class EventResourceGetResp {

    @SerializedName("isError")
    @Expose
    var isError: String? = null
    @SerializedName("hasData")
    @Expose
    var hasData: String? = null

}


class EmailInviteResp {

    @SerializedName("isError")
    @Expose
    var isError: String? = null
    @SerializedName("hasData")
    @Expose
    var hasData: String? = null

}

class ContainerIdArr {

    @SerializedName("snapshotContID")
    @Expose
    var snapshotContID: String? = null
    @SerializedName("mobileContID")
    @Expose
    var mobileContID: String? = null
    @SerializedName("profileContID")
    @Expose
    var profileContID: String? = null

}