package com.coppermobile.android.egowall.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper private constructor(context: Context) {
    private val mSharedPreferences: SharedPreferences

    var isFirstTimeLaunch: Boolean
        get() = mSharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) = mSharedPreferences.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply()

    var isLoggedIn: Boolean
        get() = mSharedPreferences.getBoolean(IS_LOGGED_IN, false)
        set(isLoggedIn) = mSharedPreferences.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()

    var isSalesforceDataFetched: Boolean
        get() = mSharedPreferences.getBoolean(IS_SALESFORCE_DATA_FETCHED, false)
        set(isSalesForceDataFetched) = mSharedPreferences.edit().putBoolean(IS_SALESFORCE_DATA_FETCHED, isSalesForceDataFetched).apply()

    init {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return mSharedPreferences.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }

    fun putInt(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return mSharedPreferences.getInt(key, 0)
    }

    fun putFloat(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return mSharedPreferences.getFloat(key, 0f)
    }

    fun putStringSet(key: String, value: MutableSet<String>) {
        mSharedPreferences.edit().putStringSet(key, value).apply()
    }

    fun getStringSet(key: String): MutableSet<String> {
        return mSharedPreferences.getStringSet(key, HashSet<String>())!!
    }

    fun clearData() {
//        mSharedPreferences.edit().clear().apply()
//        isFirstTimeLaunch = false
//        mSharedPreferences.edit().putInt(USER_TOTAL_ACHIEVED_SCORE, 0).apply()
//        mSharedPreferences.edit().putInt(USER_TOTAL_SCORE, 0).apply()
//        mSharedPreferences.edit().putString(vContext.getString(R.string.access_token), "").apply()
    }

    companion object {
        private var mSharedPreferencesHelper: SharedPreferencesHelper? = null
        private val IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch"
        private val IS_LOGGED_IN = "isLoggedIn"
        private val IS_SALESFORCE_DATA_FETCHED = "isSalesforceDataFetched"
        val selectedGenderForVoice = "SelectedGenderForVoice"
        val selectedFlag = "selectedFlag"
        private val FILE_NAME = "Bump"

        val USER_IMAGE = "UserImage"
        val USER_TOTAL_ACHIEVED_SCORE = "UserTotalAchievedScore"
        val USER_TOTAL_SCORE = "UserTotalScore"

        private lateinit var vContext: Context

        @Synchronized
        fun getInstance(vContext: Context): SharedPreferencesHelper {
            this.vContext = vContext
            if (mSharedPreferencesHelper == null) {
                mSharedPreferencesHelper = SharedPreferencesHelper(vContext)
            }
            return mSharedPreferencesHelper as SharedPreferencesHelper
        }
    }
}
