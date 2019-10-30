package com.coppermobile.android.egowall.activities

import android.os.Bundle
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.fragments.BaseFragment
import com.coppermobile.android.egowall.fragments.EmailPhoneFragment

class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        switchFragment(
            EmailPhoneFragment(),
            false,
            getString(R.string.email_phone_data)
        )
    }

    override fun switchFragment(targetFragment: BaseFragment, addToBackStack: Boolean, fragmentTag: String?) {
        super.switchFragment(targetFragment, addToBackStack, fragmentTag)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.replace(R.id.onboarding_container, targetFragment, fragmentTag)
        fragmentTransaction.commit()
    }
}
