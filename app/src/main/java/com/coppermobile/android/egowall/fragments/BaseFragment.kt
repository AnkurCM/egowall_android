package com.coppermobile.android.egowall.fragments

import androidx.fragment.app.Fragment

import com.coppermobile.android.egowall.interfaces.ISwitchListener

open class BaseFragment : Fragment() {

    private var iSwitchListener: ISwitchListener? = null


    /**
     * set switch listener
     */
    fun setISwitchListener() {
        if (iSwitchListener == null) {
            iSwitchListener = activity as ISwitchListener?
        }
    }

//    fun showNoInternet(retry: BaseConnectivityActivity.RetryCallback?) {
//        if (activity is BaseActivity) {
//            (activity as BaseActivity).showNoInternet(retry)
//        }
//    }

    /**
     * @param targetFragment fragment to switch
     * @param addToBackStack boolean for adding fragment in back stack
     * @param fragmentTag tag of fragment
     * This method is used to switch fragments on activity
     */
    fun switchFragment(targetFragment: BaseFragment, addToBackStack: Boolean, fragmentTag: String) {
        if (iSwitchListener == null) {
            setISwitchListener()
        }

        if (iSwitchListener != null) {
            iSwitchListener?.switchFragment(targetFragment, addToBackStack, fragmentTag)
        }
    }
}