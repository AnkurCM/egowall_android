package com.coppermobile.android.egowall.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.EmailPhoneViewModel
import kotlinx.android.synthetic.main.fragment_email_phone.*


class EmailPhoneFragment : BaseFragment() {
    var sharedPreferencesHelper: SharedPreferencesHelper? = null
    var emailPhoneViewModel: EmailPhoneViewModel? = null
    var signupFragment: SignupFragment? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_email_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)
        val factory = ViewModelFactory.getInstance()
        emailPhoneViewModel = ViewModelProviders.of(activity!!, factory)[EmailPhoneViewModel::class.java]


        frag_email_phone_btn_signup.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
            var entity = et_login_email.text.toString()
            if (emailPhoneViewModel!!.handleInput(entity)!!) {
                saveEntityToSP("email", entity)
                signupFragment = SignupFragment()
                switchFragment(signupFragment!!, true, getString(R.string.signup_fragment))
            }
        }

        tv_frag_email_phone_login.setOnClickListener {
            switchFragment(LoginEmailFragment(), true, getString(R.string.login))
        }
    }

    private fun saveEntityToSP(s: String, entity: String) {
        if (s == "email") {
            sharedPreferencesHelper?.putString(getString(R.string.email), entity)
        } else {
            sharedPreferencesHelper?.putString(getString(R.string.phone), entity)

        }
    }

    @SuppressLint("ResourceType")
    private fun initializeUI() {
        val redColor = Color.parseColor(getString(R.color.red_color))
        val alreadyAMember = SpannableString(getString(R.string.already_a_member_login))
        alreadyAMember.setSpan(
            ForegroundColorSpan(redColor),
            24,
            alreadyAMember.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_frag_email_phone_login.text = alreadyAMember
    }


}