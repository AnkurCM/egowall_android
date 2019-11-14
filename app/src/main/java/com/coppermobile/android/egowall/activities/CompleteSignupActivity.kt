package com.coppermobile.android.egowall.activities

import android.os.Bundle
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.fragments.CompleteSignupFragment
import com.coppermobile.android.egowall.utils.Constants

class CompleteSignupActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_signup)

        changeStatusBarColor(R.color.white)

        switchFragment(CompleteSignupFragment(), false, Constants.SIGNUP_FRAGMENT, R.id.fl_complete_signup)
    }
}
