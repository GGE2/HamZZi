package com.team.teamrestructuring.view.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.dto.Todo

class TodoAdapter (var items: MutableList<Todo>) :  RecyclerView.Adapter<TodoAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Todo){
            val todo_text = itemView.findViewById<TextView>(R.id.textview_item_todo_title)
            todo_text.text = item.content
            val todo_check = itemView.findViewById<CheckBox>(R.id.radio_item_todo_check)
            val todo_menu = itemView.findViewById<Button>(R.id.button_item_todo_modify_delete)

            // todo check 가 됐을 때
            if (item.is_check){
                todo_text.setTextColor(Color.parseColor("#c0c0c0"))
                todo_check.isChecked = true
                todo_check.isClickable = false
            }else{
                todo_check.isClickable = true
                todo_check.isChecked = false
                todo_text.setTextColor(Color.parseColor("#72402b"))
            }
            todo_menu.setOnClickListener() {
                menuClick?.onClick(todo_menu, layoutPosition)
            }

            // 버튼을 눌었을 때 이벤트 처리
            todo_check.setOnCheckedChangeListener { buttonView, isChecked ->
                // todoFragment 에서 작동
                boxClick?.onClick(todo_check, layoutPosition)

                if (item.todo_id == null) {
                    todo_check.isChecked = false
                } else {
                    // 버튼 클릭 여부에 대해서 뷰단 수정
                    if (isChecked) {
                        todo_check.isClickable = false
                        todo_text.setTextColor(Color.parseColor("#c0c0c0"))
                    } else {

                        todo_text.setTextColor(Color.parseColor("#72402b"))
                    }
                }
            }
        }
    }
    fun checkTodo(){

    }


    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    interface MemuClick {
        fun onClick(view: View, position: Int)
    }
    var menuClick: MemuClick? =null

    interface BoxClick {
        fun onClick(view: View, position: Int)
    }
    var boxClick : BoxClick? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todorecycleview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(menuClick != null){
            holder.itemView.setOnClickListener {
                    v -> menuClick?.onClick(v, position)
            }
        }
        else if(boxClick != null){
            holder.itemView.setOnClickListener {
                    v -> boxClick?.onClick(v, position)
            }
        }
        else if(itemClick != null){
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
