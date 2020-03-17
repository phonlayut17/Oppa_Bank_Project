package com.example.oppabankapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import org.json.JSONArray

class TransactionAdapter (act : FragmentActivity, val dataSource: JSONArray) : BaseAdapter() {
    private val activity : FragmentActivity = act
    private val thiscontext: Context = act.baseContext
    private val inflater: LayoutInflater = thiscontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.length()
    }

    override fun getItem(position: Int): Any {
        return dataSource.getJSONObject(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder : ViewHolder

        // 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.layout_transaction, parent, false)

            // 3
            holder = ViewHolder()
            holder.transaction_id = view.findViewById(R.id.transaction_id) as TextView
            holder.transaction_price = view.findViewById(R.id.transaction_price) as TextView
            holder.transaction_description = view.findViewById(R.id.transaction_description) as TextView

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val transaction_id = holder.transaction_id
        val transaction_price = holder.transaction_price
        val transaction_description = holder.transaction_description

        transaction_id.setText( dataSource.getJSONObject(position).getString("transaction_id").toString() )
        transaction_price.setText( dataSource.getJSONObject(position).getString("transaction_price").toString() + " บาท")
        transaction_description.setText( dataSource.getJSONObject(position).getString("transaction_description").toString() )

        view.setOnClickListener{

            //เตรียม implement ตอนแก้ไขข้อมูล
        }

        return view
    }

    private class ViewHolder {

        lateinit var transaction_id: TextView
        lateinit var transaction_price: TextView
        lateinit var transaction_description: TextView

    }

}