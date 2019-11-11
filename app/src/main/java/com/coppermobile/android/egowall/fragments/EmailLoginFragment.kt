package com.coppermobile.android.egowall.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.data.requests.LoginRequest
import com.coppermobile.android.egowall.data.responses.LoginResponse
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.net.Status
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class EmailLoginFragment : BaseFragment() {

    private var loginViewModel: LoginViewModel? = null
    var sharedPreferencesHelper: SharedPreferencesHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        loginViewModel = ViewModelProviders.of(activity!!, factory)[LoginViewModel::class.java]
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)

        loginViewModel!!.response
            .observe(this, Observer<Resource<LoginResponse>> { handleLoginResponse(it!!) })

        btn_frag_login_next.setOnClickListener {
            sendLoginRequest()
        }
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
        var password = tiet_frag_login_password.text.toString()
        var email = tiet_frag_login_email_phone.text.toString()
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
