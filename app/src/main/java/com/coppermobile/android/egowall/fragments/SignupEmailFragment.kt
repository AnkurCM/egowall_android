package com.coppermobile.android.egowall.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.activities.CompleteSignupActivity
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.EmailPhoneViewModel
import kotlinx.android.synthetic.main.fragment_signup_email.*
import kotlinx.android.synthetic.main.fragment_signup_email.tv_signup_login_option


class SignupEmailFragment : BaseFragment() {


    var sharedPreferencesHelper: SharedPreferencesHelper? = null
    var emailPhoneViewModel: EmailPhoneViewModel? = null
    var signupFragment: CompleteSignupFragment? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)
        val factory = ViewModelFactory.getInstance()
        emailPhoneViewModel = ViewModelProviders.of(activity!!, factory)[EmailPhoneViewModel::class.java]

        clickListener()
        emailTextWatcher()
    }

    private fun emailTextWatcher() {

        et_signup_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") {

                    if (et_signup_email.text.toString() != "") {
                        bt_signup_email_next.isEnabled = true
                        bt_signup_email_next.background =
                            ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                        bt_signup_email_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                    }

                } else {

                    bt_signup_email_next.isEnabled = false
                    bt_signup_email_next.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    bt_signup_email_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_signup_email.error = null

                if (s != null && s.isNotEmpty()) {
                    til_signup_email.setHintTextAppearance(R.style.LoginTextInputLayoutFilledStyle)
                    til_signup_email.boxStrokeColor = ContextCompat.getColor(activity!!, R.color.color_479EA1)
                } else {
                    til_signup_email.setHintTextAppearance(R.style.LoginTextInputLayoutStyle)
                    til_signup_email.boxStrokeColor = ContextCompat.getColor(activity!!, R.color.black)
                }

            }

        })
    }

    private fun clickListener() {

        bt_signup_email_next.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
//            val entity = et_signup_email.text.toString()
//            if (emailPhoneViewModel!!.handleInput(entity)!!) {
//                saveEntityToSP("email", entity)
//                signupFragment = CompleteSignupFragment()
//                switchFragment(signupFragment!!, true, getString(R.string.signup_fragment))
//            }
            if (validateData()) {
                sharedPreferencesHelper?.putString(getString(R.string.email), et_signup_email.text.toString())
                startActivity(Intent(activity!!, CompleteSignupActivity::class.java))
            }
        }
    }

    private fun validateData(): Boolean {

        val email = et_signup_email.text.toString()

        if (Helpers.isEmailValid(email)) {
            return true
        } else {

            if (email == "" || !Helpers.isEmailValid(email)) {
                til_signup_email.error = getString(R.string.check_your_email)
            }
        }
        return false

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
        val spanColor = Color.parseColor(getString(R.color.color_F3A248))
        val alreadyAMember = SpannableString(getString(R.string.already_a_member_login))
        alreadyAMember.setSpan(
            ForegroundColorSpan(spanColor),
            24,
            alreadyAMember.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_signup_login_option.text = alreadyAMember
    }


}