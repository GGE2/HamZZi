package com.team.teamrestructuring.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.dto.Todo

class TodoAdapter (var items: MutableList<Todo>) :  RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Todo){
            val todo_text = itemView.findViewById<TextView>(R.id.recycler_item)
            todo_text.text = item.content
//            todo_text.id = item.todo_id.toInt()
        }
    }
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todorecycleview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(itemClick != null){
            holder.itemView.setOnClickListener{
                    v -> itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
