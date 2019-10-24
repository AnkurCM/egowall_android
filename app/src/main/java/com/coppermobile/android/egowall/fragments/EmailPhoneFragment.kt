package com.coppermobile.android.egowall.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.factories.ViewModelFactory
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.SharedPreferencesHelper
import com.coppermobile.android.egowall.viewmodels.SignupViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

class EmailPhoneFragment : BaseFragment() {
    var sharedPreferencesHelper: SharedPreferencesHelper? = null
    var signupViewModel: SignupViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(activity!!)
        val factory = ViewModelFactory.getInstance()
        signupViewModel = ViewModelProviders.of(activity!!, factory)[SignupViewModel::class.java]


        frag_signup_btn_signup.setOnClickListener {
            Helpers.hideKeyboard(activity!!)
            var valid = verifyEmailOrPhone()
            if(valid){
                saveToSP()
            }else{

            }
        }
    }

    private fun saveToSP() {

    }

    private fun verifyEmailOrPhone():Boolean {
        return true
    }

    private fun initializeUI() {

    }


}