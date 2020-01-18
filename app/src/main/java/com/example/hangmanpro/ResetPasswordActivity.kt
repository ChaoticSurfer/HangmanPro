package com.example.hangmanpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        supportActionBar!!.hide()


        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        resetPassword.setOnClickListener {
            val emailToSend = email.text.toString()
            if (TextUtils.isEmpty(emailToSend) ||  "@" !in emailToSend) {

                Toast.makeText(this, "Please enter correct email", Toast.LENGTH_LONG).show()



            }
            else {
            auth.sendPasswordResetEmail(emailToSend)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                            .show()
                        finish()
                    } else {
                        Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                            .show()
                    }
                })


            }
            }

    }
}
