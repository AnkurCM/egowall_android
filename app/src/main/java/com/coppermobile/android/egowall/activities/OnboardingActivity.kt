package com.coppermobile.android.egowall.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_onboarding.*
import com.coppermobile.android.egowall.R
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.coppermobile.android.egowall.fragments.LoginEmailFragment
import com.coppermobile.android.egowall.fragments.LoginPhoneNumberFragment


class OnboardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        changeStatusBarColor(R.color.white)

        val tv = LayoutInflater.from(this@OnboardingActivity).inflate(R.layout.tab_textview, null) as TextView

//        tb_onboarding.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                vp_onboarding.currentItem = tab.position
//                tv.typeface = ResourcesCompat.getFont(this@OnboardingActivity, R.font.mulibold)
//                tab.customView = tv
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
////                val customView = tab.customView as TextView
////                customView.typeface = ResourcesCompat.getFont(this@OnboardingActivity, R.font.muliregular)
////                tab.customView = customView
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//                tv.typeface = ResourcesCompat.getFont(this@OnboardingActivity, R.font.mulibold)
//                tab.customView = tv
//            }
//        })

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        vp_onboarding.adapter = viewPagerAdapter

        tb_onboarding.setupWithViewPager(vp_onboarding)
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = LoginEmailFragment()
                1 -> fragment = LoginPhoneNumberFragment()
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
