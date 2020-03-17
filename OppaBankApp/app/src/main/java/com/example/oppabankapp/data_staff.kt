package com.example.oppabankapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class data_staff : Fragment() {

    private var detail_name:String?=null
    private var detail_facebook:String?=null
//    private var detail_position:String?=null
    private var detail_img:String?=null
    private var detail_line:String?=null
    private var detail_tel:String?=null

    fun newInstance(
        detail_name: String,
        detail_facebook: String,
//        detail_position: String,
        detail_img: String,
        detail_line: String,
        detail_tel: String
    ): data_staff {
        val fragment = data_staff()
        val bundle = Bundle()
        bundle.putString("detail_name", detail_name)
        bundle.putString("detail_facebook", detail_facebook)
//        bundle.putString("detail_position", detail_position)
        bundle.putString("img", detail_img)
        bundle.putString("detail_line", detail_line)
        bundle.putString("detail_tel", detail_tel)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data_staff, container, false)
        val detail_name : TextView = view.findViewById(R.id.detail_name)
        val detail_facebook : TextView = view.findViewById(R.id.detail_facebook)
//        val detail_position : TextView = view.findViewById(R.id.detail_position)
        val detail_img : ImageView = view.findViewById(R.id.detail_img)
        val detail_line : TextView = view.findViewById(R.id.detail_line)
        val detail_tel : TextView = view.findViewById(R.id.detail_tel)

        detail_name.text = this.detail_name
        detail_facebook.text = this.detail_facebook
//        detail_position.text = this.detail_position
        detail_line.text = this.detail_line
        detail_tel.text = this.detail_tel
        Glide.with(activity!!.baseContext)
            .load(detail_img)
            .into(detail_img)

        val button_close : Button = view.findViewById(R.id.button_close);

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_staff = staff()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_staff, "fragment_staff")
            transaction.addToBackStack("fragment_staff")
            transaction.commit()
        }

        return view;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            detail_name = bundle.getString("detail_name").toString()
            detail_facebook = bundle.getString("detail_facebook").toString()
//            detail_position = bundle.getString("detail_position").toString()
            detail_img = bundle.getString("detail_img").toString()
            detail_line = bundle.getString("detail_line").toString()
            detail_tel = bundle.getString("detail_tel").toString()
        }
    }


}
