package com.peter.guessthenumber.framework.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.guessthenumber.model.Level

class LevelSelectionViewModel : ViewModel() {
    private val _level = MutableLiveData<Level?>()
    val level: LiveData<Level?>
        get() = _level

    fun setLevel1(){
        _level.value = Level(1,"Easy",500,5)
    }

    fun setLevel2(){
        _level.value = Level(2,"Medium",1000,2)
    }

    fun setLevel3(){
        _level.value = Level(3,"Hard",10000,1)
    }

    init {
        _level.value = null
    }

    fun onNavigationComplete(){
        _level.value = null
    }
}