package com.team.teamrestructuring.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.teamrestructuring.util.ApplicationClass


class HomeViewModel : ViewModel() {

    private val _exp: MutableLiveData<Int> = MutableLiveData()
    val exp : LiveData<Int>
        get() = _exp

    fun update(data:Int){
        _exp.postValue(data)
    }


}