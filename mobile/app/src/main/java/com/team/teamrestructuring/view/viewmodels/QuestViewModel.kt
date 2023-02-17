package com.team.teamrestructuring.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.teamrestructuring.dto.DailyQuest

class QuestViewModel : ViewModel(){

    private val _data:MutableLiveData<List<DailyQuest>> = MutableLiveData()
    val data:LiveData<List<DailyQuest>>
        get() = _data

    fun updateQuestData(data:List<DailyQuest>){
        _data.postValue(data)
    }
}