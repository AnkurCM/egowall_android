package com.coppermobile.android.egowall.interfaces

import com.coppermobile.android.egowall.fragments.BaseFragment

/**
 * Created by Noopur on 23, October, 2019
 */
interface ISwitchListener {
    fun switchFragment(targetFragment: BaseFragment, addToBackStack: Boolean, fragmentTag: String?)
}