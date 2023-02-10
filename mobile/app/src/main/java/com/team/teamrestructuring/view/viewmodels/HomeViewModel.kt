package com.team.teamrestructuring.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel(){

    var _list : MutableLiveData<String> = MutableLiveData()
    val list : LiveData<String> get() = _list




}