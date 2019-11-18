package com.coppermobile.android.egowall.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.fragments.SignupEmailFragment
import com.coppermobile.android.egowall.fragments.SignupPhoneFragment
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        changeStatusBarColor(R.color.white)

        val viewPagerAdapter = SignupViewPagerAdapter(supportFragmentManager)
        vp_signup.adapter = viewPagerAdapter

        tb_signup.setupWithViewPager(vp_signup)

    }

    inner class SignupViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = SignupEmailFragment()
                1 -> fragment = SignupPhoneFragment()
            }
            return fragment!!
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var title: String? = null
            when (position) {
                0 -> title = getString(R.string.email)
                1 -> title = getString(R.string.phone_number)
            }
            return title
        }
    }

}
