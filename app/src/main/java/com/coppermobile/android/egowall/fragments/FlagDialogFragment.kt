package com.coppermobile.android.egowall.fragments


import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.adapters.FlagAdapter
import com.coppermobile.android.egowall.interfaces.ICloseFragment
import com.coppermobile.android.egowall.interfaces.IFlagUpdates
import com.coppermobile.android.egowall.utils.Helpers
import kotlinx.android.synthetic.main.fragment_flag_dialog.*
import java.util.ArrayList

class FlagDialogFragment(private var iFlagUpdates: IFlagUpdates) : DialogFragment(), ICloseFragment, IFlagUpdates {

    override fun flagDetail(flagDetail: String) {
        iFlagUpdates.flagDetail(flagDetail)
    }

    override fun close() {
        Helpers.hideKeyboard(activity!!)
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flag_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flagAdapter = FlagAdapter(activity!!, this, this)

        rv_flag.layoutManager = LinearLayoutManager(activity!!)
        rv_flag.adapter = flagAdapter

        if (arguments != null) {
            val flagList: ArrayList<String>? = arguments?.getStringArrayList(getString(R.string.flag_list))
            if (flagList != null) {
                flagAdapter.addData(flagList)
            }
        }

        et_flag_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Do something
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Do something
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flagAdapter.getFilter().filter(s)
            }

        })

    }
}
