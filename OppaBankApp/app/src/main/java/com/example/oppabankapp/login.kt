package com.example.oppabankapp


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class login : Fragment() {
    private var username_text: EditText? = null
    private var password_text: EditText? = null

    var user : FirebaseUser? = null
    lateinit var facebookSignInButton : LoginButton
    var callbackManager : CallbackManager? = null
    // Firebase Auth Object.
    var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_login, container, false)

        callbackManager = CallbackManager.Factory.create()
        facebookSignInButton  = view.findViewById(R.id.login_button) as LoginButton
        firebaseAuth = FirebaseAuth.getInstance()
        facebookSignInButton.setReadPermissions("email")

        // If using in a fragment
        facebookSignInButton.setFragment(this)

        val token: AccessToken?
        token = AccessToken.getCurrentAccessToken()

        if (token != null) { //Means user is not logged in
            LoginManager.getInstance().logOut()
        }

        // Callback registration
        facebookSignInButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) { // App code

                handleFacebookAccessToken(loginResult!!.accessToken)

            }
            override fun onCancel() { // App code
            }
            override fun onError(exception: FacebookException) { // App code
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code

                    handleFacebookAccessToken(loginResult!!.accessToken)

                }

                override fun onCancel() { // App code
                }

                override fun onError(exception: FacebookException) { // App code
                }
            })


        username_text = view.findViewById(R.id.username)
        password_text = view.findViewById(R.id.password)

        val button_login : Button = view.findViewById(R.id.button_login);
        val button_contact_us : Button = view.findViewById(R.id.button_contact_us);

        button_login.setOnClickListener {
            if (username_text!!.text.toString() == "60160025" && password_text!!.text.toString() == "60160025") {
                Toast.makeText(context, "LOGIN SUCCESS.", Toast.LENGTH_LONG).show()
                val fragment_home_page_normal = home_page_normal()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, fragment_home_page_normal, "fragment_home_page_normal")
                transaction.addToBackStack("fragment_home_page_normal")
                transaction.commit()
            } else if (username_text!!.text.toString() != "" && password_text!!.text.toString() != "" && username_text!!.text.toString() != "60160025" && password_text!!.text.toString() != "60160025") {
                Toast.makeText(context, "LOGIN FAIL, PLEASE CHECK USERNAME AND PASSWORD AGAIN.", Toast.LENGTH_LONG).show()
            } else if (username_text!!.text.toString() == "" && password_text!!.text.toString() == "") {
                Toast.makeText(context, "PLEASE INPUT USERNAME AND PASSWORD.", Toast.LENGTH_LONG).show()
            } else if (username_text!!.text.toString() == "") {
                Toast.makeText(context, "PLEASE INPUT USERNAME.", Toast.LENGTH_LONG).show()
            } else if (password_text!!.text.toString() == "") {
                Toast.makeText(context, "PLEASE INPUT PASSWORD.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "LOGIN FAIL, PLEASE CHECK USERNAME AND PASSWORD AGAIN.", Toast.LENGTH_LONG).show()
            }
        }

        button_contact_us.setOnClickListener {
            Toast.makeText(context, "You Choose Contact Us.", Toast.LENGTH_LONG).show()
            val fragment_contact_us = contact()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragment_contact_us, "fragment_contact_us")
            transaction.addToBackStack("fragment_contact_us")
            transaction.commit()
        }
        // Inflate the layout for this fragment
        return view;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(activity!!.baseContext)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token : AccessToken) {


        Log.d(TAG, "handleFacebookAccessToken:" + token)
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")

                user = firebaseAuth!!.currentUser

                val main_home_page = main_home_page().newInstance(user!!.photoUrl.toString(),user!!.displayName.toString())
                val fm = fragmentManager
                val transaction : FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, main_home_page,"main_home_page")
                transaction.addToBackStack("main_home_page")
                transaction.commit()

            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException())
                Toast.makeText(activity!!.baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }




}
