package com.coppermobile.android.egowall.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.activities.OnboardingActivity
import com.coppermobile.android.egowall.interfaces.AlertDialogCallback
import java.text.SimpleDateFormat
import java.util.regex.Pattern

/**
 * Created by Noopur on 23, October, 2019
 */

object Helpers {

    private val YESTERDAY = "Yesterday"
    private val TODAY = "Today"
    private val TOMORROW = "Tomorrow"
    private val CALENDAR_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss"
    private val VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", Pattern.CASE_INSENSITIVE)

    var isConnected = false

    /**
     * Method is used to show alert dialog containing title, message, positive button, negative button and perform task on click of buttons.
     * */
    fun showAlertDialog(
        activity: Activity,
        title: String,
        message: String,
        posBtnText: String,
        negBtnText: String,
        alertDialogCallback: AlertDialogCallback?
    ): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        val alert = builder.create()
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(posBtnText) { _, _ ->
            alert.dismiss()
            alert.hide()
            alertDialogCallback?.posCallback()
        }
        builder.setNegativeButton(negBtnText) { _, _ ->
            alert.dismiss()
            alertDialogCallback?.negCallback()
        }

        builder.setCancelable(false)
        return builder.show()
    }


    fun checkConnectivity(context: Context?) {
        isConnected =
            isNetworkConnected(context)
    }

    /**
     * Method is used to check internet connectivity.
     * We need ACCESS_NETWORK_STATE permission for checking internet connectivity.
     * */
    fun isNetworkConnected(context: Context?): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected)
    }

    /**
     * Method is used to validate email is in right pattern oir not.
     * */
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()
    }

    /**
     * Method is used to validate email is password is 6 character long or not.
     * */
    fun isValidPassword(password: String): Boolean {
        return password.length in 8..15
    }


    fun getDisplayMetrics(context: Activity?): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        context?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics

    }


    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun openLogin(context: Context?) {
        SharedPreferencesHelper.getInstance(context!!)
            .putBoolean(context.getString(R.string.is_logged_in), false)
        val intent = Intent(context, OnboardingActivity::class.java)
        intent.putExtra(context.getString(R.string.open_login), true)
        context.startActivity(intent)
    }


    fun dateFormat(fromDateFormat: String, todateFormat: String, date: String): String? {

        val sdf = SimpleDateFormat(fromDateFormat)
        val parse = sdf.parse(date)

        val sdf1 = SimpleDateFormat(todateFormat)
        return sdf1.format(parse)
    }
}