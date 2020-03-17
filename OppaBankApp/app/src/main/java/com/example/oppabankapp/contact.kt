package com.example.oppabankapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class contact : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_contact, container, false)

        val button_close_fragment : Button = view.findViewById(R.id.button_close);
        button_close_fragment.setOnClickListener {
            val fragment_login = login()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_login, "fragment_login")
            transaction.addToBackStack("fragment_login")
            transaction.commit()
        }
        // Inflate the layout for this fragment
        return view;
    }


}
