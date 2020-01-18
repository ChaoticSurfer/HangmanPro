package com.example.hangmanpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_changepass.*

class ChangepassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepass)
        supportActionBar!!.hide()

        val fa: FirebaseAuth = FirebaseAuth.getInstance()

        changePassword.setOnClickListener {
            val password = password.text.toString()

            if(TextUtils.isEmpty(password))
                Toast.makeText(this,"Please fill in the field",Toast.LENGTH_SHORT).show()
            else if (password.length <= 7 )
            {Toast.makeText(this, "Password must consist of at least 8 letters", Toast.LENGTH_LONG).show()}

            else{
                fa.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_LONG)
                                .show()
                            finish()

                        } else {

                            Toast.makeText(this, "Password not changed!", Toast.LENGTH_LONG)
                                .show()

                        }
                    })



            }

        }


    }
}
