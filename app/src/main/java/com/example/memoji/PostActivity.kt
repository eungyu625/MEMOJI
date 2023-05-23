package com.example.memoji

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.memoji.data.MemoData

class PostActivity: AppCompatActivity() {

    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        dataBaseHelper = DataBaseHelper(this)
        val intentByPrev = intent

        val titleView: TextView = findViewById(R.id.view_title)
        val contentView: TextView = findViewById(R.id.view_content)
        val listBtn: Button = findViewById(R.id.listBtn)
        val removeBtn: Button = findViewById(R.id.removeBtn)

        val memo: MemoData? = dataBaseHelper.getMemo(intent.getIntExtra("id", 0))

        titleView.text = memo?.title
        contentView.text = memo?.content

        listBtn.setOnClickListener {
            val nextIntent = Intent(this, MemoActivity::class.java)
            startActivity(nextIntent)
        }

        removeBtn.setOnClickListener {
            dataBaseHelper.deleteMemo(intent.getIntExtra("id", 0))
            val nextIntent = Intent(this, MemoActivity::class.java)
            startActivity(nextIntent)
        }

    }
}