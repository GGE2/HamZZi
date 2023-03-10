package com.team.teamrestructuring.view.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.dto.Todo

private const val TAG = "TodoAdapter_지훈"
class TodoAdapter (var items: MutableList<Todo>) :  RecyclerView.Adapter<TodoAdapter.ViewHolder>(){
    private lateinit var todoItemClickListener : TodoItemClickListener
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Todo){
            val todo_text = itemView.findViewById<TextView>(R.id.textview_item_todo_title)
            todo_text.text = item.content
            val todo_check = itemView.findViewById<CheckBox>(R.id.radio_item_todo_check)
            // todo check 가 됐을 때
            Log.d(TAG, "bindItems: ${item.toString()}")
            todo_check.setOnClickListener {
                todoItemClickListener.onClick(it,layoutPosition,item)
            }
            if (item.ischeck){
                todo_text.setTextColor(Color.parseColor("#c0c0c0"))
                todo_check.isChecked = true
                todo_check.isClickable = false
            }else{
                todo_check.isClickable = true
                todo_check.isChecked = false
                todo_text.setTextColor(Color.parseColor("#72402b"))
            }
        }
    }

    fun setOnTodoClickListener(todoItemClickListener:TodoItemClickListener){
        this.todoItemClickListener = todoItemClickListener
    }

    interface TodoItemClickListener {
        fun onClick(view: View, position: Int,data:Todo)
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todorecycleview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(items[position])
    }


    override fun getItemCount(): Int {
        return items.size
    }
}
