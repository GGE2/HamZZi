package com.team.teamrestructuring.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.dto.Todo

class TodoAdapter (var items: MutableList<Todo>) :  RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Todo){
            val todo_text = itemView.findViewById<TextView>(R.id.textview_item_todo_title)
            todo_text.text = item.content
//            todo_text.id = item.todo_id.toInt()

            val todo_menu = itemView.findViewById<Button>(R.id.button_item_todo_modify_delete)
            todo_menu.setOnClickListener() {
                menuClick?.onClick(todo_menu, layoutPosition)
            }
        }
    }


    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    interface MemuClick {
        fun onClick(view: View, position: Int)
    }
    var menuClick: MemuClick? =null


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
        //
        else if(menuClick != null){
            holder.itemView.setOnClickListener {
                v -> menuClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }


    override fun getItemCount(): Int {
        return items.size
    }

}
