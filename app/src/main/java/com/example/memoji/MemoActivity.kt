package com.example.memoji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memoji.data.MemoData
import java.util.Collections

class MemoActivity: AppCompatActivity() {

    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MemoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        dataBaseHelper = DataBaseHelper(this)

        val memoList = dataBaseHelper.getAllMemos()
        val sortedMemoList = memoList.toMutableList().sortedByDescending { it.date }

        viewManager = LinearLayoutManager(this)
        viewAdapter = MemoAdapter(sortedMemoList, this)


        val writeBtn: Button = findViewById(R.id.writeBtn)
        val logoutBtn: Button = findViewById(R.id.logoutBtn)


        recyclerView = findViewById<RecyclerView?>(R.id.memo_rv).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

        writeBtn.setOnClickListener {
            val nextIntent = Intent(this, WriteActivity::class.java)
            startActivity(nextIntent)
        }

        logoutBtn.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }
}