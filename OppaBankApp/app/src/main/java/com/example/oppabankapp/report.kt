package com.example.oppabankapp


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class report : Fragment() {
    private var report_text: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_report, container, false)

        report_text = view.findViewById(R.id.data_description_report)

        val button_send_report : Button = view.findViewById(R.id.button_send_report);
        val button_close : Button = view.findViewById(R.id.button_close);
        val button_list_problem : Button = view.findViewById(R.id.button_list_problem);

        // Inflate the layout for this fragment

        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
//        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")


        button_send_report.setOnClickListener {
            if (report_text!!.text.toString() != "" && report_text!!.text.toString() != "ระบุข้อความ") {
                //input data to realtime database
                val mMessagesRef2 = mRootRef.child("data_loan")

                val key = mMessagesRef.push().key
                val postValues: HashMap<String, Any> = HashMap()
                postValues["username"] = "แบงค์ หงิด"
                postValues["report_data"] = report_text!!.text.toString()

                val childUpdates: MutableMap<String, Any> = HashMap()

                childUpdates["$key"] = postValues

                mMessagesRef2.updateChildren(childUpdates)

                Toast.makeText(context, "ระบบได้ทำการบันทึกปัญหาเรียบร้อยแล้ว.", Toast.LENGTH_LONG).show()
                val fragment_ShowData = ShowData()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, fragment_ShowData,"fragment_ShowData")
                transaction.addToBackStack("fragment_ShowData")
                transaction.commit()
            } else if (report_text!!.text.toString() != "" || report_text!!.text.toString() != "ระบุข้อความ") {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
                builder.setTitle("ระบบรับเรื่องร้องเรียน")
                builder.setMessage("กรุณากรอกข้อมูล")
                builder.setNegativeButton("ปิด",
                    DialogInterface.OnClickListener { dialog, which ->
                        //dialog.dismiss();
                    })
                builder.show()
            }
        }

        button_close.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_main_home_page = main_home_page()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_main_home_page, "fragment_main_home_page")
            transaction.addToBackStack("fragment_main_home_page")
            transaction.commit()
        }

        button_list_problem.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            Toast.makeText(context, "เข้าสู่รายการข้อมูลปัญหาของคุณสำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_ShowData = ShowData()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_ShowData,"fragment_ShowData")
            transaction.addToBackStack("fragment_ShowData")
            transaction.commit()
        }

        return view;
    }


}
