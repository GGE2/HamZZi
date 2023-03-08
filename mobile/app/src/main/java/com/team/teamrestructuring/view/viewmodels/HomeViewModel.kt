package com.team.teamrestructuring.view.viewmodels

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.PetData
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.util.ApplicationClass


class HomeViewModel : ViewModel() {

    private val _petData: MutableLiveData<PetData> = MutableLiveData()
    val petData : LiveData<PetData>
        get() = _petData

    private val _dailyQuestData:MutableLiveData<List<DailyQuest>> = MutableLiveData()
    val dailyQuestData : LiveData<List<DailyQuest>>
        get() = _dailyQuestData

    private val _userData:MutableLiveData<User> = MutableLiveData()
    val userData : LiveData<User>
        get() = _userData

    private val _todoData:MutableLiveData<MutableList<Todo>> = MutableLiveData()


    val todoData : LiveData<MutableList<Todo>>
        get() = _todoData

    private val _exp : MutableLiveData<Int> = MutableLiveData()
    val exp : LiveData<Int>
        get() = _exp

    fun updateQuest(data:List<DailyQuest>){
        _dailyQuestData.value = data
    }


    fun updatePet(data:PetData){
        _petData.postValue(data)
    }
    fun updateExp(data:Int){
        _exp.postValue(data)
    }
    fun updateUser(data:User){
        _userData.postValue(data)
    }
    fun updateTodo(data:MutableList<Todo>){
        _todoData.value = data
    }


}