package com.coppermobile.android.egowall.fragments

import android.os.Bundle
import android.text.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.data.requests.LoginRequest
import com.coppermobile.android.egowall.data.responses.LoginResponse
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.net.Status
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_email_login.*
import android.text.method.PasswordTransformationMethod
import androidx.core.content.ContextCompat
import com.coppermobile.android.egowall.utils.Helpers
import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.coppermobile.android.egowall.R


class EmailLoginFragment : BaseFragment() {

    private var loginViewModel: LoginViewModel? = null
    var sharedPreferencesHelper: SharedPreferencesHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        loginViewModel = ViewModelProviders.of(activity!!, factory)[LoginViewModel::class.java]
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)

        loginViewModel!!.response
            .observe(this, Observer<Resource<LoginResponse>> { handleLoginResponse(it!!) })

        frag_email_phone_btn_signup.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
            if (validateData()) {

            }
            sendLoginRequest()
        }

        tiet_frag_password.transformationMethod = PasswordTransformationMethod()

        emailTextWatchers()
        passwordTextWatchers()
        rootTouchListener()
        spanText()
    }

    private fun spanText() {

        val signUpText = tv_frag_email_phone_login.text.toString()

        val ss = SpannableString(signUpText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
               Toast.makeText(activity!!, getString(R.string.static_open_signup_activity), Toast.LENGTH_SHORT).show()
            }
        }
        ss.setSpan(clickableSpan, 23, signUpText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ss.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(activity!!, R.color.color_F3A248)),
            23,
            signUpText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tv_frag_email_phone_login.text = ss
    }

    private fun rootTouchListener() {
        rl_login_email_root_container.setOnTouchListener { _, _ ->
            Helpers.hideKeyboard(activity!!)
            true
        }
    }

    private fun validateData(): Boolean {
        val email = tiet_frag_email_phone.text.toString()
        val password = tiet_frag_password.text.toString()

        if (Helpers.isEmailValid(email) && Helpers.isValidPassword(password)) {
            return true
        } else {

            if (email == "" || !Helpers.isEmailValid(email)) {
                til_frag_email_phone.error = getString(R.string.check_your_email)
            }
            if (password == "" || !Helpers.isValidPassword(password)) {
                til_frag_password.error = getString(R.string.check_your_password)
            }
        }
        return false
    }


    private fun emailTextWatchers() {
        tiet_frag_email_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "" && tiet_frag_password.text.toString() != "") {
                    frag_email_phone_btn_signup.isEnabled = true
                    frag_email_phone_btn_signup.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    frag_email_phone_btn_signup.setTextColor(ContextCompat.getColor(activity!!, R.color.white))


                } else {
                    frag_email_phone_btn_signup.isEnabled = false
                    frag_email_phone_btn_signup.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    frag_email_phone_btn_signup.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_frag_email_phone.error = null
            }

        })
    }


    private fun passwordTextWatchers() {
        tiet_frag_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "" && tiet_frag_email_phone.text.toString() != "") {
                    frag_email_phone_btn_signup.isEnabled = true
                    frag_email_phone_btn_signup.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    frag_email_phone_btn_signup.setTextColor(ContextCompat.getColor(activity!!, R.color.colorPrimary))
                } else {
                    frag_email_phone_btn_signup.isEnabled = false
                    frag_email_phone_btn_signup.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    frag_email_phone_btn_signup.setTextColor(ContextCompat.getColor(activity!!, R.color.colorAccent))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_frag_password.error = null
            }

        })
    }


    private fun handleLoginResponse(resource: Resource<LoginResponse>) {
        when (resource.status) {
            Status.LOADING -> {
//                showLoading(true)
//                enableButton(false)
                Log.i("aa", "Aal")
            }
//            Status.ERROR -> showError(resource.message!!)
            Status.IDLE -> {
//                showLoading(false)
                Log.i("aa", "Aai")
            }
            Status.SUCCESS -> {
                Log.i("aa", "Aas")
//                if (resource.data != null) {
//                    if (resource.data.isSuccessful) {
//                        val args = Bundle()
//                        args.putString(getString(R.string.email), et_enter_email.text.toString())
//                        logInFragment!!.arguments = args
//                        switchFragment(logInFragment!!, false, getString(R.string.login_in))
//                        enableButton(true)
//                    } else {
//                        showMessage(resource.message!!)
//                        enableButton(true)
//                    }
//                } else {
//                    showMessage(getString(R.string.email_id_not_registered_with_us))
//                    enableButton(true)
//                }
            }

            Status.ERROR -> {
                Log.i("aa", "Aae")

            }
        }
    }

    private fun sendLoginRequest() {
        var password = tiet_frag_password.text.toString()
        var email = tiet_frag_email_phone.text.toString()
        var loginRequest = LoginRequest()
        loginRequest.userEmail = email
        loginRequest.password = password
        loginRequest.format = "json"
        loginViewModel?.getLoginResponse(loginRequest)

    }

    private fun showLoading(show: Boolean) {
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }


}
