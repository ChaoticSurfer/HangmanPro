package com.example.hangmanpro

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({},600)
        setContentView(R.layout.activity_main)
        val sharedPref: SharedPreferences = getSharedPreferences("sharedprefs",0)
        val fb: FirebaseAuth = FirebaseAuth.getInstance()
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        val dbUsers: DatabaseReference = db.getReference("users")
        val dbChallenges: DatabaseReference = db.getReference("challenges")
        title = sharedPref.getString("Nickname",null)


        btnChallenges.setOnClickListener{
            val intent = Intent(this, ChallengesActivity::class.java)
            startActivity(intent)
        }
        btnCreateChallenge.setOnClickListener{

            val intent = Intent(this, CreateChallengeActivity::class.java)
            startActivity(intent)



        }

        btnChangePass.setOnClickListener {
            val intent = Intent(this, ChangepassActivity::class.java)
            startActivity(intent)
        }

        btnLogOut.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
            sharedPref.edit().clear().commit()

        }

        btnDeleteAccount.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(intent)
            fb.currentUser!!.delete()
            dbUsers.child(sharedPref.getString("Nickname",null)!!).removeValue()
            dbChallenges.child(sharedPref.getString("Nickname", null)!!).removeValue()
            sharedPref.edit().clear().commit()


        }
    }
}
