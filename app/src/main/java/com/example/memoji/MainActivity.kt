package com.example.memoji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 아이디 : B375368
        // 비밀번호 : 1234
        val loginBtn: Button = findViewById(R.id.loginbutton)
        val userId: EditText = findViewById(R.id.userId)
        val password: EditText = findViewById(R.id.password)

        loginBtn.setOnClickListener {
            if (userId.text.toString() == "B735368" && password.text.toString() == "1234") {
                Log.d("Login", "SUCCESS!")
                val nextIntent = Intent(this, MemoActivity::class.java)

                startActivity(nextIntent)
            }
        }
    }
}