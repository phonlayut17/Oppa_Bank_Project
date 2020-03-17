package com.example.oppabankapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class map : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_map, container, false)

        val button_close : Button = view.findViewById(R.id.button_close);
        // Inflate the layout for this fragment

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_main_home_page = main_home_page()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_main_home_page, "fragment_main_home_page")
            transaction.addToBackStack("fragment_main_home_page")
            transaction.commit()
        }

        val button_info : Button = view.findViewById(R.id.button_info);

        button_info.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val ShowDataHttp = ShowDataHttp()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, ShowDataHttp, "fragment_ShowDataHttp")
            transaction.addToBackStack("fragment_ShowDataHttp")
            transaction.commit()
        }

        return view;
    }


}
