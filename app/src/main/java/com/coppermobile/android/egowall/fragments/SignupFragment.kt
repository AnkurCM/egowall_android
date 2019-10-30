package com.coppermobile.android.egowall.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.data.requests.SignupRequest
import com.coppermobile.android.egowall.data.responses.SignupResponse
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.net.Resource
import com.coppermobile.android.egowall.net.Status
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.SignupViewModel
import kotlinx.android.synthetic.main.fragment_signup.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SignupFragment : BaseFragment() {

    private var signupViewmodel: SignupViewModel? = null
    var sharedPreferencesHelper: SharedPreferencesHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        signupViewmodel = ViewModelProviders.of(activity!!, factory)[SignupViewModel::class.java]
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)

        signupViewmodel!!.response
            .observe(this, Observer<Resource<SignupResponse>> { handleSignupRespnose(it!!) })

        btn_frag_signup.setOnClickListener {
            sendRegisterRequest()
        }
    }

    private fun sendRegisterRequest() {
        var fn = tiet_frag_signup_fn.text.toString()
        var ln = tiet_frag_signup_ln.text.toString()
        var email = sharedPreferencesHelper?.getString(getString(R.string.email))
        var password = tiet_frag_signup_password.text.toString()
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
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }


}
