package com.project.albus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.project.albus.ui.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        init()
    }

    private fun init() {
        val currentUser = auth.currentUser
        if (currentUser==null){
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}