package com.example.oppabankapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {


    private val thiscontext : Context = fragmentActivity.baseContext
//    private val thisActivity = fragmentActivity


    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var detail_name: TextView
        lateinit var detail_position: TextView
        lateinit var detail_facebook: TextView
        lateinit var detail_line: TextView
        lateinit var detail_tel: TextView
        lateinit var detail_img: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            detail_name = View.findViewById<View>(R.id.tv_name) as TextView
            detail_position = View.findViewById<View>(R.id.tv_description) as TextView
            detail_facebook = View.findViewById<View>(R.id.tv_name) as TextView
            detail_line = View.findViewById<View>(R.id.tv_description) as TextView
            detail_tel = View.findViewById<View>(R.id.tv_tel) as TextView
            detail_img = View.findViewById<View>(R.id.imgV) as ImageView

        }

    }


    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

//        var detail_name = dataSource.getJSONObject(position).getString("name").toString()
//        var detail_position = dataSource.getJSONObject(position).getString("position").toString()
//        var detail_facebook = dataSource.getJSONObject(position).getString("facebook").toString()
//        var detail_line = dataSource.getJSONObject(position).getString("line").toString()
//        var detail_tel = dataSource.getJSONObject(position).getString("tel").toString()
//        var detail_img = dataSource.getJSONObject(position).getString("image").toString()
//        var detail_image = dataSource.getJSONObject(position).getString("image").toString()

        holder.detail_name.setText( dataSource.getJSONObject(position).getString("name").toString() )
        holder.detail_position.setText( dataSource.getJSONObject(position).getString("position").toString() )
        holder.detail_tel.setText( dataSource.getJSONObject(position).getString("tel").toString() )


        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.detail_img)

        holder.layout.setOnClickListener{

            Toast.makeText(thiscontext,holder.detail_facebook.text.toString(),Toast.LENGTH_SHORT).show()
//
//            val data_detail: Fragment = data_staff().newInstance(
//                detail_name,
//                detail_position,
//                detail_facebook,
//                detail_line,
//                detail_tel
//
//            )
//            val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
//            val transaction = manager.beginTransaction()
//            transaction.replace(R.id.layout, data_detail, "fragment_data_staff")
//            transaction.replace(R.id.layout, data_detail)
//            transaction.addToBackStack("fragment_data_staff")
//            transaction.commit()
        }

    }



}
