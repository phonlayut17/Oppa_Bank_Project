package com.example.oppabankapp


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager

/**
 * A simple [Fragment] subclass.
 */
class main_home_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_main_home_page, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView

        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

        val button_loan : Button = view.findViewById(R.id.button_loan);
        val button_stock : Button = view.findViewById(R.id.button_stock);
        val button_map : Button = view.findViewById(R.id.button_map);
        val button_report : Button = view.findViewById(R.id.button_report);
        val button_info : Button = view.findViewById(R.id.button_info);
        val button_logout : Button = view.findViewById(R.id.button_logout);

        button_loan.setOnClickListener {
            Toast.makeText(context, "เข้าสู่เมนูโอนเงินสำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_loan = loan()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_loan, "fragment_loan")
            transaction.addToBackStack("fragment_loan")
            transaction.commit()
        }

        button_stock.setOnClickListener {
            Toast.makeText(context, "เข้าสู่เมนูหุ้นวันนี้สำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_MainChart = MainChart()
            val fm = fragmentManager
            val transaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_MainChart,"fragment_MainChart")
            transaction.addToBackStack("fragment_MainChart")
            transaction.commit()
//            Toast.makeText(context, "เข้าสู่เมนูตรวจสอบยอดเงินสำเร็จ.", Toast.LENGTH_LONG).show()
//            val fragment_data = data()
//            val fm = fragmentManager
//            val transaction: FragmentTransaction = fm!!.beginTransaction()
//            transaction.replace(R.id.layout, fragment_data, "fragment_data")
//            transaction.addToBackStack("fragment_data")
//            transaction.commit()
        }

        button_map.setOnClickListener {
            Toast.makeText(context, "เข้าสู่เมนูแผนที่ธนาคารสำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_map = map()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_map, "fragment_map")
            transaction.addToBackStack("fragment_map")
            transaction.commit()
        }

        button_report.setOnClickListener {
            Toast.makeText(context, "เข้าสู่เมนูรายงานปัญหาสำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_report = report()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_report, "fragment_report")
            transaction.addToBackStack("fragment_report")
            transaction.commit()
        }

        button_info.setOnClickListener {
            Toast.makeText(context, "เข้าสู่เมนูติดต่อเจ้าหน้าที่สำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_staff = staff()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_staff, "fragment_staff")
            transaction.addToBackStack("fragment_staff")
            transaction.commit()
        }

        button_logout.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("คุณแน่ใจว่าต้องการออกจากระบบหรือไม่?")
            builder.setPositiveButton("ตกลง",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(context, "LOGOUT SUCCESS.", Toast.LENGTH_LONG).show()
                    val fragment_login = login()
                    val fm = fragmentManager
                    val transaction: FragmentTransaction = fm!!.beginTransaction()
                    transaction.replace(R.id.layout, fragment_login, "fragment_login")
                    transaction.addToBackStack("fragment_login")
                    transaction.commit()
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()
        }

        // Inflate the layout for this fragment
        return view;
    }

    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): main_home_page {

        val profile = main_home_page()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)

        return profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }

    }



}
