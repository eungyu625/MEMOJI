package com.example.memoji

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.memoji.data.MemoData
import com.example.memoji.data.WriteData
import java.text.SimpleDateFormat
import java.util.*

class DataBaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        const val DATABASE_NAME = "Memo.db"
        const val TABLE_NAME = "memo_table"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT, DATE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        context.deleteDatabase(DATABASE_NAME)
        onCreate(db)
    }

    fun insert(memo: WriteData): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("TITLE", memo.title)
        contentValues.put("CONTENT", memo.content)
        contentValues.put("DATE", memo.date.toString())
        Log.d("WriteData", "${memo.title}, ${memo.content}, ${memo.date.toString()}")
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getAllMemos(): List<MemoData> {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val memos = mutableListOf<MemoData>()
        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex("ID")
                val index = cursor.getInt(idIndex)
                val titleIndex = cursor.getColumnIndex("TITLE")
                val contentIndex = cursor.getColumnIndex("CONTENT")
                val dateIndex = cursor.getColumnIndex("DATE")
                val title = cursor.getString(titleIndex)
                val content = cursor.getString(contentIndex)
                val dateString = cursor.getString(dateIndex)
                val date = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'XXX yyyy", Locale.US).parse(dateString)
                memos.add(MemoData(index, title, content, date))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return memos ?: emptyList()
    }

    fun getMemo(id: Int): MemoData? {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID = ?", arrayOf(id.toString()))
        var memo: MemoData? = null
        if (cursor.moveToFirst()) {
            val index = cursor.getColumnIndex("ID")
            val titleIndex = cursor.getColumnIndex("TITLE")
            val contentIndex = cursor.getColumnIndex("CONTENT")
            val dateIndex = cursor.getColumnIndex("DATE")
            val title = cursor.getString(titleIndex)
            val content = cursor.getString(contentIndex)
            val dateString = cursor.getString(dateIndex)
            val date = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'XXX yyyy", Locale.US).parse(dateString)
            memo = MemoData(index, title, content, date)
        }
        cursor.close()
        return memo
    }

    fun deleteMemo(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id.toString()))
    }
}