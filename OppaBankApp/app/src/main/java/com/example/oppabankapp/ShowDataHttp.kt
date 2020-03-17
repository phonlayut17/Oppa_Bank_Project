package com.example.oppabankapp

import HttpGetRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class ShowDataHttp : Fragment() {

    lateinit var textview : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_show_data_http, container, false)
//        val view = inflater.inflate(R.layout.fragment_show_data_http, container, false)
        // Inflate the layout for this fragment

        //Some url endpoint that you may have
        val myUrl = "https://en.wikipedia.org/wiki/Web"

        //String to place our result in
        val result : String

        //Instantiate new instance of our class
        val getRequest = HttpGetRequest()

        //Perform the doInBackground method, passing in our url
        result = getRequest.execute(myUrl).get()

        textview = view.findViewById(R.id.text_http)
        textview.setText(result)

        val button_close : Button = view.findViewById(R.id.button_close);

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_map = map()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_map, "fragment_map")
            transaction.addToBackStack("fragment_map")
            transaction.commit()
        }

        return view
    }


}
