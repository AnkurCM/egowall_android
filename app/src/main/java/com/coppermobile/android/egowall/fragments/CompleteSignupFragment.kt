package com.coppermobile.android.egowall.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.net.Status
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.SignupViewModel
import kotlinx.android.synthetic.main.fragment_complete_signup.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CompleteSignupFragment : BaseFragment() {

    private var signupViewmodel: SignupViewModel? = null
    var sharedPreferencesHelper: SharedPreferencesHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        signupViewmodel = ViewModelProviders.of(activity!!, factory)[SignupViewModel::class.java]
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)

        signupViewmodel!!.response
            .observe(this, Observer<Resource<SignupResponse>> { handleSignupRespnose(it!!) })


        clickListeners()
        firstNameTextWatcher()
        lastNameTextWatcher()
        passwordTextWatcher()
        confirmPasswordTextWatcher()
    }


    private fun firstNameTextWatcher() {

        et_complete_signup_first_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") {

                    if (et_complete_signup_last_name.text.toString() != "" && et_complete_signup_password.text.toString() != "" && et_complete_signup_confirm_password.text.toString() != "") {
                        btn_complete_signup_next.isEnabled = true
                        btn_complete_signup_next.background =
                            ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                        btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                    }

                } else {

                    btn_complete_signup_next.isEnabled = false
                    btn_complete_signup_next.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_complete_signup_first_name.error = null

                if (s != null && s.isNotEmpty()) {
                    til_complete_signup_first_name.setHintTextAppearance(R.style.LoginTextInputLayoutFilledStyle)
                    til_complete_signup_first_name.boxStrokeColor =
                        ContextCompat.getColor(activity!!, R.color.color_479EA1)
                } else {
                    til_complete_signup_first_name.setHintTextAppearance(R.style.LoginTextInputLayoutStyle)
                    til_complete_signup_first_name.boxStrokeColor = ContextCompat.getColor(activity!!, R.color.black)
                }
            }

        })
    }


    private fun lastNameTextWatcher() {

        et_complete_signup_last_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") {

                    if (et_complete_signup_first_name.text.toString() != "" && et_complete_signup_password.text.toString() != "" && et_complete_signup_confirm_password.text.toString() != "") {
                        btn_complete_signup_next.isEnabled = true
                        btn_complete_signup_next.background =
                            ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                        btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                    }

                } else {

                    btn_complete_signup_next.isEnabled = false
                    btn_complete_signup_next.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_complete_signup_last_name.error = null

                if (s != null && s.isNotEmpty()) {
                    til_complete_signup_last_name.setHintTextAppearance(R.style.LoginTextInputLayoutFilledStyle)
                    til_complete_signup_last_name.boxStrokeColor =
                        ContextCompat.getColor(activity!!, R.color.color_479EA1)
                } else {
                    til_complete_signup_last_name.setHintTextAppearance(R.style.LoginTextInputLayoutStyle)
                    til_complete_signup_last_name.boxStrokeColor = ContextCompat.getColor(activity!!, R.color.black)
                }
            }

        })
    }


    private fun passwordTextWatcher() {

        et_complete_signup_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") {

                    if (et_complete_signup_first_name.text.toString() != "" && et_complete_signup_last_name.text.toString() != "" && et_complete_signup_confirm_password.text.toString() != "") {
                        btn_complete_signup_next.isEnabled = true
                        btn_complete_signup_next.background =
                            ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                        btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                    }

                } else {

                    btn_complete_signup_next.isEnabled = false
                    btn_complete_signup_next.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_complete_signup_password.error = null

                if (s != null && s.isNotEmpty()) {
                    til_complete_signup_password.setHintTextAppearance(R.style.LoginTextInputLayoutFilledStyle)
                    til_complete_signup_password.boxStrokeColor =
                        ContextCompat.getColor(activity!!, R.color.color_479EA1)
                } else {
                    til_complete_signup_password.setHintTextAppearance(R.style.LoginTextInputLayoutStyle)
                    til_complete_signup_password.boxStrokeColor = ContextCompat.getColor(activity!!, R.color.black)
                }
            }

        })
    }


    private fun confirmPasswordTextWatcher() {

        et_complete_signup_confirm_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") {

                    if (et_complete_signup_first_name.text.toString() != "" && et_complete_signup_password.text.toString() != "" && et_complete_signup_last_name.text.toString() != "") {
                        btn_complete_signup_next.isEnabled = true
                        btn_complete_signup_next.background =
                            ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                        btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                    }

                } else {

                    btn_complete_signup_next.isEnabled = false
                    btn_complete_signup_next.background =
                        ContextCompat.getDrawable(activity!!, R.drawable.btn_login_filled)
                    btn_complete_signup_next.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_complete_signup_confirm_password.error = null

                if (s != null && s.isNotEmpty()) {
                    til_complete_signup_confirm_password.setHintTextAppearance(R.style.LoginTextInputLayoutFilledStyle)
                    til_complete_signup_confirm_password.boxStrokeColor =
                        ContextCompat.getColor(activity!!, R.color.color_479EA1)
                } else {
                    til_complete_signup_confirm_password.setHintTextAppearance(R.style.LoginTextInputLayoutStyle)
                    til_complete_signup_confirm_password.boxStrokeColor =
                        ContextCompat.getColor(activity!!, R.color.black)
                }
            }

        })
    }


    private fun clickListeners() {
        btn_complete_signup_next.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
            if (validateData()) {
//                sendRegisterRequest()

                til_complete_signup_first_name.error = null
                til_complete_signup_last_name.error = null
                til_complete_signup_password.error = null
                til_complete_signup_confirm_password.error = null
            }
        }
    }


    private fun validateData(): Boolean {

        val firstName = et_complete_signup_first_name.text.toString()
        val lastName = et_complete_signup_last_name.text.toString()
        val password = et_complete_signup_password.text.toString()
        val confirmPassword = et_complete_signup_confirm_password.text.toString()

        if (Helpers.isNameValid(firstName) && Helpers.isNameValid(lastName) && Helpers.isPasswordValid(password) && Helpers.isPasswordValid(
                confirmPassword
            ) && password == confirmPassword
        ) {
            return true
        } else {

            if (firstName == "" || !Helpers.isNameValid(firstName)) {
                til_complete_signup_first_name.error = getString(R.string.first_name_required)
            }
            if (lastName == "" || !Helpers.isNameValid(lastName)) {
                til_complete_signup_last_name.error = getString(R.string.last_name_required)
            }
            if (password == "" || !Helpers.isPasswordValid(password)) {
                til_complete_signup_password.error = getString(R.string.password_error)
            }
            if (confirmPassword == "" || !Helpers.isPasswordValid(confirmPassword)) {
                til_complete_signup_confirm_password.error = getString(R.string.password_error)
            } else if (password != confirmPassword) {
                til_complete_signup_confirm_password.error = getString(R.string.password_doesnt_match)
                til_complete_signup_password.error = " "
            }
        }
        return false

    }


    private fun sendRegisterRequest() {
        var fn = et_complete_signup_first_name.text.toString()
        var ln = et_complete_signup_last_name.text.toString()
        var email = sharedPreferencesHelper?.getString(getString(R.string.email))
        var password = et_complete_signup_password.text.toString()
        var signupRequest = SignupRequest()
        signupRequest.firstName = fn
        signupRequest.lastName = ln
        signupRequest.userEmail = email
        signupRequest.password = password
        signupRequest.format = "json"
        signupViewmodel?.getRegistrationResponse(signupRequest)
    }

    private fun handleSignupRespnose(resource: Resource<SignupResponse>) {
        when (resource.status) {
            Status.LOADING -> {
                showLoading(true)
//                enableButton(false)
                Log.i("aa", "Aal")
            }
//            Status.ERROR -> showError(resource.message!!)
            Status.IDLE -> {
                showLoading(false)
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

    private fun showLoading(show: Boolean) {
        pb_complete_signup.visibility = if (show) View.VISIBLE else View.GONE
    }


}
