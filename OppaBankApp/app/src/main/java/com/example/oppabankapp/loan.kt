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
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class loan : Fragment() {
    private var data_id: EditText? = null
    private var data_price: EditText? = null
    private var data_description: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_loan, container, false)

        data_id = view.findViewById(R.id.data_id)
        data_price = view.findViewById(R.id.data_price)
        data_description = view.findViewById(R.id.data_description)

        val button_send_loan : Button = view.findViewById(R.id.button_send_loan);
        val button_list_loan : Button = view.findViewById(R.id.button_list_trans);
        val button_close : Button = view.findViewById(R.id.button_close);
        // Inflate the layout for this fragment

        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")

        button_send_loan.setOnClickListener {
            //            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            if (data_id!!.text.toString() != "" && data_id!!.text.toString() != "ระบุเลขบัญชี"
                && data_price!!.text.toString() != "" && data_price!!.text.toString() != "ระบุจำนวนเงิน"
                && data_description!!.text.toString() != "" && data_description!!.text.toString() != "ระบุข้อความ") {

                //input data to realtime database
                val mMessagesRef2 = mRootRef.child("transaction_data")

                val key = mMessagesRef.push().key
                val postValues: HashMap<String, Any> = HashMap()
                postValues["transaction_id"] = data_id!!.text.toString()
                postValues["transaction_price"] = data_price!!.text.toString()
                postValues["transaction_description"] = data_description!!.text.toString()

                val childUpdates: MutableMap<String, Any> = HashMap()

                childUpdates["$key"] = postValues

                mMessagesRef2.updateChildren(childUpdates)

            Toast.makeText(context, "คุณทำรายการโอนเงินสำเร็จ.", Toast.LENGTH_LONG).show()
                val fragment_show_transaction = show_transaction()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, fragment_show_transaction, "fragment_show_transaction")
                transaction.addToBackStack("fragment_show_transaction")
                transaction.commit()
            }
            if (data_id!!.text.toString() == "") {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
                builder.setTitle("OPPA BANK")
                builder.setMessage("กรุณากรอกเลขบัญชี.")
                builder.setNegativeButton("ปิด",
                    DialogInterface.OnClickListener { dialog, which ->
                        //dialog.dismiss();
                    })
                builder.show()
            } else if (data_price!!.text.toString() == "") {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
                builder.setTitle("OPPA BANK")
                builder.setMessage("กรุณากรอกจำนวนเงิน.")
                builder.setNegativeButton("ปิด",
                    DialogInterface.OnClickListener { dialog, which ->
                        //dialog.dismiss();
                    })
                builder.show()
            } else if (data_description!!.text.toString() == "") {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
                builder.setTitle("OPPA BANK")
                builder.setMessage("กรุณากรอกรายละเอียด.")
                builder.setNegativeButton("ปิด",
                    DialogInterface.OnClickListener { dialog, which ->
                        //dialog.dismiss();
                    })
                builder.show()
            } else if (data_id!!.text.toString() == "ระบุเลขบัญชี" || data_price!!.text.toString() == "ระบุจำนวนเงิน" || data_description!!.text.toString() == "ระบุข้อความ") {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
                builder.setTitle("OPPA BANK")
                builder.setMessage("กรุณากรอกข้อมูล.")
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

        button_list_loan.setOnClickListener {
            Toast.makeText(context, "เข้าสู่รายการการโอนเงินของคุณสำเร็จ.", Toast.LENGTH_LONG).show()
            val fragment_show_transaction = show_transaction()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_show_transaction, "fragment_show_transaction")
            transaction.addToBackStack("fragment_show_transaction")
            transaction.commit()
        }

        return view;
    }


}
