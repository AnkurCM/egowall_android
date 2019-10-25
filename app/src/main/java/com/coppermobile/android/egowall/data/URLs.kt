package com.coppermobile.android.egowall.data

import com.coppermobile.android.egowall.BuildConfig


/**
 * Created by Sunita on 07, February, 2019
 * This class contains all the URLs of used apis in application together with the base url.
 */
class URLs private constructor() {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL

        const val REGISTER = "api/user/add"
        const val LOGIN = "api/user/login"
        const val ACTIVATION_CODE = "api/user/activate"
        const val RESEND_ACTIVATION_CODE = "api/user/resendotp"
        const val FORGOT_PASSWORD = "api/user/forgot"
        const val CHANGE_PASSWORD = "api/user/changepassword"
        const val LOGOUT = "api/user/logout"
        const val COUNTRY_LIST = "api/user/countrylist"
        const val LANGUAGE_LIST = "api/user/languagelist"
        const val GET_PROFILE = "api/user/profile"
        const val UPDATE_PROFILE = "api/user/edit"
        const val UPLOAD_PROFIC_PIC = "api/upload/user/profilePic"
        const val EMAIL_WORD_LIST = "api/user/emailword"
        const val FAQ = "api/user/faq"

        const val GOOGLE_TRANSLATION =
            "https://translation.googleapis.com/language/translate/v2?key=AIzaSyBcqVbh1omqD_-EmLssA8UPHUfowq9FWuU"
        const val TEXT_TO_SPEECH = "https://texttospeech.googleapis.com/v1/text:synthesize"
        const val SPEECH = "https://speech.googleapis.com/v1/speech:recognize"

        const val FILTER = "api/song/search"
        const val HOME_SONGS_LIST = "api/song/list"
        const val HOME_PLAYLIST = "api/song/playlist"
        const val HOME_HISTORY = "api/song/history"
        const val SHOW_FILTER_DATA = "api/song/filterdata"
        const val ADD_TO_PLAYLIST = "api/song/addplaylist"
        const val SEARCH_SONG = "api/song/search"
        const val SONG_LIKE_DISLIKE = "api/song/like"
        const val SONG_DETAIL = "api/song/detail"
        const val SET_SCORE = "api/song/savescore"
        const val GET_SCORE = "api/song/score"
        const val WORD_LIST = "api/song/scorelist"

        const val NOTIFICATION = "api/notification/list"
        const val DELETE_SINGLE_NOTIFICATION = "api/notification/delete"
        const val DELETE_ALL_NOTIFICATION = "api/notification/deleteall"
        const val NOTIFICATION_COUNT = "api/notification/count"

        const val ADD_GUEST_TRIAL = "api/guest/updatetrial"
        const val USER_SUBSCRIPTION = "api/user/subscribe"
        const val VALIDATE_FREE_TRIAL = "api/guest/validatetrial"
    }
}
