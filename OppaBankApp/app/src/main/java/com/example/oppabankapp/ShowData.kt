package com.example.oppabankapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class ShowData : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_show_data, container, false)
        // Inflate the layout for this fragment

        val mRootRef = FirebaseDatabase.getInstance().reference
        val mMessagesRef = mRootRef.child("data")

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = JSONArray()
                val listView = view.findViewById<ListView>(R.id.listView)

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val username = ds.child("username").getValue(String::class.java)!!
                    val text = ds.child("report_data").getValue(String::class.java)!!

                    jObject.put("key",ds.key)
                    jObject.put("username",username)
                    jObject.put("report_data",text)

                    list.put(jObject)

                }

                val adapter = Adapter(activity!!, list)

                listView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        val button_close : Button = view.findViewById(R.id.button_close);

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_report = report()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_report, "fragment_report")
            transaction.addToBackStack("fragment_report")
            transaction.commit()
        }



        return view
    }


}
