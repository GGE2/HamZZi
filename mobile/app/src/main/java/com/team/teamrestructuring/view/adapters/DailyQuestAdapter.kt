package com.team.teamrestructuring.view.adapters

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.databinding.ItemQuestBinding
import com.team.teamrestructuring.dto.DailyQuest

class DailyQuestAdapter(var datas:MutableList<DailyQuest>) : RecyclerView.Adapter<DailyQuestAdapter.DailyQuestViewHolder>(){
    private lateinit var questClickListener : QuestClickListener
    inner class DailyQuestViewHolder(var binding:ItemQuestBinding) : RecyclerView.ViewHolder(binding.root){
        val tv = binding.textviewItemQuestTitle
        fun bindData(data:DailyQuest){
            binding.radioItemQuestCheck.isChecked = data.is_checked
            binding.radioItemQuestCheck.isClickable = false
            tv.text = data.title
            if(data.is_checked){
                tv.setTextColor(Color.parseColor("#c0c0c0"))
                binding.buttonItemQuestFinish.text = "수행완료"
            }else{
                tv.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyQuestViewHolder {
        val binding = ItemQuestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DailyQuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyQuestViewHolder, position: Int) {
        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
    interface QuestClickListener{
        fun onClick(view: View,position:Int,data:DailyQuest)
    }
    fun setOnQuestClickListener(questClickListener: QuestClickListener){
        this.questClickListener = questClickListener
    }
}