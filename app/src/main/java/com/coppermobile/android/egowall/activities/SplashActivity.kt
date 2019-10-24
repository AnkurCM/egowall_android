package com.coppermobile.android.egowall.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.SignupActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    fun goToSignup(view: View){
        var intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}
