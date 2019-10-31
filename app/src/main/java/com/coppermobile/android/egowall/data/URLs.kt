package com.coppermobile.android.egowall.data

import com.coppermobile.android.egowall.BuildConfig


/**
 * Created by Sunita on 07, February, 2019
 * This class contains all the URLs of used apis in application together with the base url.
 */
class URLs private constructor() {

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL

        const val REGISTER = "register"
        const val LOGIN = "login"
    }
}
