package com.coppermobile.android.egowall.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import android.view.WindowManager
import android.os.Build


class SplashActivity : BaseActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000


    private var sharedPrefs: SharedPreferencesHelper? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

//            if (!sharedPrefs!!.isFirstTimeLaunch) {
//                if (sharedPrefs!!.isLoggedIn) {
//                    val resultIntent = Intent(this, HomeActivity::class.java)
//                    startActivity(resultIntent)
//                } else {
            startActivity(Intent(this, SignupActivity::class.java))
//                }

//            }
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setFullScreenActivity()

        sharedPrefs = SharedPreferencesHelper.getInstance(this)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
