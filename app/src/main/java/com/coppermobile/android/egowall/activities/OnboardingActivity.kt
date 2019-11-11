package com.coppermobile.android.egowall.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.coppermobile.android.egowall.fragments.BaseFragment
import kotlinx.android.synthetic.main.activity_onboarding.*
import androidx.fragment.app.FragmentPagerAdapter
import com.coppermobile.android.egowall.R
import androidx.fragment.app.FragmentManager
import com.coppermobile.android.egowall.fragments.EmailLoginFragment
import com.coppermobile.android.egowall.fragments.PhoneNumberLoginFragment


class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
//        switchFragment(
//            EmailPhoneFragment(),
//            false,
//            getString(R.string.email_phone_data)
//        )

//        vp_onboarding.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tb_onboarding))


//        tb_onboarding.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                vp_onboarding.currentItem = tab.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//
//            }
//        })

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        vp_onboarding.adapter = viewPagerAdapter

        tb_onboarding.setupWithViewPager(vp_onboarding)
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


    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = EmailLoginFragment()
                1 -> fragment = PhoneNumberLoginFragment()
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
