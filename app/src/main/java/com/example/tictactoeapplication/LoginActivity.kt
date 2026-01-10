package com.example.tictactoeapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<android.view.View>(R.id.btnLetsPlay).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
