package com.coppermobile.android.egowall.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.utils.Helpers
import kotlinx.android.synthetic.main.layout_no_internet.*

@SuppressLint("Registered")
open class BaseConnectivityActivity : AppCompatActivity() {

    private val connectivityReceiver: ConnectivityReceiver = ConnectivityReceiver()
    private var noInternetFragment: Dialog? = null

    interface RetryCallback {
        fun onRetry()

        fun onCancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Helpers.checkConnectivity(this)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(connectivityReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    inner class ConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Helpers.checkConnectivity(context)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(connectivityReceiver)
    }

    fun showNoInternet(retry: RetryCallback?) {
        if (noInternetFragment != null && noInternetFragment!!.isShowing) {
            return
        }
        if (noInternetFragment == null) {
            noInternetFragment = Dialog(this, android.R.style.Theme_Light_NoTitleBar)
            noInternetFragment!!.setContentView(R.layout.layout_no_internet)
            noInternetFragment!!.btn_try_again.setOnClickListener {
                hideNoInternet()
                retry?.onRetry()
            }
//            noInternetFragment!!.btn_cancel.setOnClickListener {
//                hideNoInternet()
//                retry?.onCancel()
//            }
            noInternetFragment!!.setCancelable(false)
        }
        noInternetFragment!!.show()
    }

    fun hideNoInternet() {
        if (noInternetFragment != null && noInternetFragment!!.isShowing && Helpers.isConnected) {
            noInternetFragment!!.dismiss()
            noInternetFragment = null
        }
    }
}