package com.example.memoji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.memoji.data.WriteData
import java.util.Date

class WriteActivity: AppCompatActivity() {

    private lateinit var databaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        databaseHelper = DataBaseHelper(this)
        val saveBtn: Button = findViewById(R.id.saveBtn)
        val backBtn: Button = findViewById(R.id.backBtn)

        saveBtn.setOnClickListener {

            val title: String = findViewById<EditText?>(R.id.title).text.toString()
            val content: String = findViewById<EditText?>(R.id.content).text.toString()
            val date = Date()

            Log.d("TITLE", "$title")
            Log.d("CONTENT", "$content")
            databaseHelper.insert(WriteData(title, content, date))

            val nextIntent = Intent(this, MemoActivity::class.java)
            startActivity(nextIntent)
        }

        backBtn.setOnClickListener {
            val nextIntent = Intent(this, MemoActivity::class.java)
            startActivity(nextIntent)
        }
    }
}