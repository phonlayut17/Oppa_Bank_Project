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
class show_transaction : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_show_transaction, container, false)
        // Inflate the layout for this fragment

        val mRootRef = FirebaseDatabase.getInstance().reference
        val mMessagesRef = mRootRef.child("transaction_data")

        mMessagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val list = JSONArray()
                val listView = view.findViewById<ListView>(R.id.listView)

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val transaction_id = ds.child("transaction_id").getValue(String::class.java)!!
                    val transaction_price = ds.child("transaction_price").getValue(String::class.java)!!
                    val transaction_description = ds.child("transaction_description").getValue(String::class.java)!!

                    jObject.put("key",ds.key)
                    jObject.put("transaction_id",transaction_id)
                    jObject.put("transaction_price",transaction_price)
                    jObject.put("transaction_description",transaction_description)

                    list.put(jObject)

                }

                val adapter = TransactionAdapter(activity!!, list)

                listView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        val button_close : Button = view.findViewById(R.id.button_close);

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_loan = loan()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_loan, "fragment_loan")
            transaction.addToBackStack("fragment_loan")
            transaction.commit()
        }



        return view
    }


}
