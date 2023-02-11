package com.team.teamrestructuring.view.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.databinding.ItemQuest2Binding
import com.team.teamrestructuring.databinding.ItemQuestBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.WeeklyQuest

class WeeklyQuestAdapter(var datas : List<WeeklyQuest>) : RecyclerView.Adapter<WeeklyQuestAdapter.WeeklyViewHolder>(){
    private lateinit var questClickListener : WeeklyQuestClickListener

    inner class WeeklyViewHolder(var binding : ItemQuest2Binding) : RecyclerView.ViewHolder(binding.root){
        val tv = binding.textviewItemQuestTitle2
        fun bindData(data: WeeklyQuest){
            binding.radioItemQuestCheck2.isChecked = data.ischeck
            binding.radioItemQuestCheck2.isClickable = false
            binding.buttonItemQuestFinish2.setOnClickListener {
                questClickListener.onClick(it,layoutPosition,data)
            }
            tv.text = data.quest.content
            if(data.ischeck){
                tv.setTextColor(Color.parseColor("#c0c0c0"))
                binding.buttonItemQuestFinish2.text = "미션종료"
                binding.buttonItemQuestFinish2.setTextColor(Color.parseColor("#c0c0c0"))
                binding.buttonItemQuestFinish2.isClickable = false
            }else{
                binding.buttonItemQuestFinish2.isClickable = true
                binding.buttonItemQuestFinish2.text = "미션하기"
                tv.setTextColor(Color.parseColor("#72402b"))
                binding.buttonItemQuestFinish2.setTextColor(Color.parseColor("#72402b"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyViewHolder {

        val binding = ItemQuest2Binding.inflate(LayoutInflater.from(parent.context),parent,false)

        return WeeklyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeeklyViewHolder, position: Int) {
        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    interface WeeklyQuestClickListener{
        fun onClick(view: View, position:Int, data:WeeklyQuest)
    }
    fun setOnQuestClickListener(questClickListener: WeeklyQuestClickListener){
        this.questClickListener = questClickListener
    }
}