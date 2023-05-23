package com.example.memoji

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memoji.data.MemoData

class MemoAdapter(private val list: List<MemoData>, private val context: Context) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var dataBaseHelper: DataBaseHelper
        val titleView: TextView = itemView.findViewById(R.id.title_contain)
        val idView: TextView = itemView.findViewById(R.id.id_contain)
        val deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)
        // Your view holder implementation
        init {
            dataBaseHelper = DataBaseHelper(context)
            itemView.setOnClickListener {
                val intent = Intent(context, PostActivity::class.java)
                intent.putExtra("id", idView.text.toString().toInt())
                context.startActivity(intent)
            }

            deleteBtn.setOnClickListener {
                dataBaseHelper.deleteMemo(idView.text.toString().toInt())
                val intent = Intent(context, MemoActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memoData = list[position]
        holder.idView.text = memoData.id.toString()
        holder.titleView.text = memoData.title
        // bind data to your views
    }

    override fun getItemCount(): Int {
        return list.size
    }
}