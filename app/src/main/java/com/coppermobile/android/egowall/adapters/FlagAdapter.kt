package com.coppermobile.android.egowall.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.interfaces.ICloseFragment
import com.coppermobile.android.egowall.interfaces.IFlagUpdates
import kotlinx.android.synthetic.main.layout_flag.view.*


/**
 *Created by Noopur on 11/13/2019.
 */
class FlagAdapter(
    private val context: Context,
    private val iCloseFragment: ICloseFragment,
    private val iFlagUpdates: IFlagUpdates
) :
    RecyclerView.Adapter<FlagAdapter.ViewHolder>() {

    var flagList: ArrayList<String>? = null
    var flagListFiltered: ArrayList<String>? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(list: ArrayList<String>) {
        flagList = list
        flagListFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_flag, parent, false))
    }

    override fun getItemCount(): Int {
        return if (flagListFiltered != null && flagListFiltered!!.isNotEmpty()) flagListFiltered!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_flag_country_name.text = flagListFiltered!![position]
        holder.itemView.setOnClickListener {
            iCloseFragment.close()
            iFlagUpdates.flagDetail(flagListFiltered!![position])
        }

        if (position == flagListFiltered!!.size - 1) {
            holder.itemView.view_flag.visibility = View.GONE
        } else {
            holder.itemView.view_flag.visibility = View.VISIBLE
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                flagListFiltered = if (charString.isEmpty()) {
                    flagList
                } else {
                    val filteredList = ArrayList<String>()
                    for (row in flagList!!) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = flagListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                flagListFiltered = filterResults.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }

}