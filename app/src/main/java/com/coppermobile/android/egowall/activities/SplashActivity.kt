package com.coppermobile.android.egowall.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper

class SplashActivity : AppCompatActivity() {

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
