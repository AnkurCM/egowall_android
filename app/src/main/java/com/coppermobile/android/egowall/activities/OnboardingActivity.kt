package com.coppermobile.android.egowall.activities

import android.os.Bundle
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.fragments.BaseFragment
import com.coppermobile.android.egowall.fragments.SignupEmailFragment

class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        switchFragment(SignupEmailFragment(), false, getString(R.string.email_phone_data), R.id.onboarding_container)
    }
}
