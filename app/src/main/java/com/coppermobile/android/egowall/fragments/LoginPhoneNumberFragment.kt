package com.coppermobile.android.egowall.fragments


import android.os.Bundle
import android.text.*
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.data.requests.LoginRequest
import com.coppermobile.android.egowall.data.responses.LoginResponse
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.interfaces.IFlagUpdates
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.net.Status
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_phone_number_login.*


class LoginPhoneNumberFragment : Fragment(), IFlagUpdates {
    override fun flagDetail(flagDetail: String) {
        Toast.makeText(activity!!, flagDetail, Toast.LENGTH_SHORT).show()
    }

    private var loginViewModel: LoginViewModel? = null
    var sharedPreferencesHelper: SharedPreferencesHelper? = null

    private val flagDialogFragment = FlagDialogFragment(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        loginViewModel = ViewModelProviders.of(activity!!, factory)[LoginViewModel::class.java]
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)

        loginViewModel!!.response
            .observe(this, Observer<Resource<LoginResponse>> { handleLoginResponse(it!!) })

        bt_login.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
            if (validateData()) {

            }
            sendLoginRequest()
        }


        phoneNumberTextWatcher()
        passwordTextWatcher()
        rootTouchListener()
        spanText()
        clickListener()
    }

    private fun clickListener() {

        val staticFlagList = ArrayList<String>()
        staticFlagList.add("United States")
        staticFlagList.add("United Kingdom")
        staticFlagList.add("India")
        staticFlagList.add("France")

        et_login_phone_number.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList(getString(R.string.flag_list), staticFlagList)
            flagDialogFragment.arguments = bundle

            val ft = fragmentManager?.beginTransaction()

            ft?.replace(R.id.rl_login_phone_number_root_container, flagDialogFragment)
            ft?.commit()
        }
    }

    private fun spanText() {

        val signUpText = tv_login_account.text.toString()

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

        tv_login_account.text = ss
    }


    private fun rootTouchListener() {
        rl_login_phone_number_root_container.setOnTouchListener { _, _ ->
            Helpers.hideKeyboard(activity!!)
            flagDialogFragment.dialog?.dismiss()
            false
        }
    }

    private fun validateData(): Boolean {
        val phoneNumber = et_login_phone_number.text.toString()
        val password = et_login_password.text.toString()

        if (Helpers.isPhoneValid(phoneNumber) && Helpers.isValidPassword(password)) {
            return true
        } else {

            if (phoneNumber == "" || !Helpers.isPhoneValid(phoneNumber)) {
                til_login_phone_number.error = getString(R.string.check_your_phone_number)
            }
            if (password == "" || !Helpers.isValidPassword(password)) {
                til_login_password.error = getString(R.string.check_your_password)
            }
        }
        return false
    }


    private fun phoneNumberTextWatcher() {
        et_login_phone_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "" && et_login_password.text.toString() != "") {
                    bt_login.isEnabled = true
                    bt_login.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    bt_login.setTextColor(ContextCompat.getColor(activity!!, R.color.white))


                } else {
                    bt_login.isEnabled = false
                    bt_login.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    bt_login.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_login_phone_number.error = null
            }

        })
    }


    private fun passwordTextWatcher() {
        et_login_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "" && et_login_phone_number.text.toString() != "") {
                    bt_login.isEnabled = true
                    bt_login.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    bt_login.setTextColor(ContextCompat.getColor(activity!!, R.color.colorPrimary))
                } else {
                    bt_login.isEnabled = false
                    bt_login.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    bt_login.setTextColor(ContextCompat.getColor(activity!!, R.color.colorAccent))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_login_password.error = null
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
        var password = et_login_password.text.toString()
        var phoneNumber = et_login_phone_number.text.toString()
        var loginRequest = LoginRequest()
//        loginRequest.userEmail = email
//        loginRequest.password = password
//        loginRequest.format = "json"
//        loginViewModel?.getLoginResponse(loginRequest)

    }

    private fun showLoading(show: Boolean) {
        login_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}
