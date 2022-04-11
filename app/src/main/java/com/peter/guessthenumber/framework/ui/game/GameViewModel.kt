package com.peter.guessthenumber.framework.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.guessthenumber.model.Level
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.ArrayList

class GameViewModel : ViewModel() {
    private val _level = MutableLiveData<Level?>()
    val level: LiveData<Level?>
        get() = _level

    private val _showPlayAgain = MutableLiveData<Boolean>()
    val showPlayAgain: LiveData<Boolean>
        get() = _showPlayAgain

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back

    private val _share = MutableLiveData<Boolean>()
    val share: LiveData<Boolean>
        get() = _share


    val title = MutableLiveData<String>()
    val status = MutableLiveData<String?>()
    private val gameNumber = MutableLiveData<Int>()
    var userAttempts = MutableLiveData<Int>(0)
    val userNumber = MutableLiveData<String>()
    private val gameNumbers = MutableLiveData<ArrayList<Int>>()

    init {
        _level.value = Level()
        gameNumbers.value = ArrayList()
        userAttempts.value = 0
        status.value = null
        _back.value = false
        _showPlayAgain.value = false
    }

    fun setLevel(level: Level) {
        _level.value = level
        title.value = "Choose a Number From ${_level.value!!.min} to ${_level.value!!.max}"
        for (i in _level.value!!.min.._level.value!!.max)
            if (i % _level.value!!.i == 0)
                gameNumbers.value!!.add(i)
        gameNumber.value = gameNumbers.value!!.asSequence().shuffled().find { true }
        Log.i("RandomNumber", gameNumber.value.toString())
    }

    fun onGuessClicked() {
        try {
            val number = userNumber.value!!.toInt()
            if (userNumber.value != null) {
                if (number > gameNumber.value!!)
                    status.value = "High"
                if (number < gameNumber.value!!)
                    status.value = "Low"
                if (number == gameNumber.value!!) {
                    status.value = "Congratulations"
                    _showPlayAgain.value = true
                }
            }
            userAttempts.value = userAttempts.value!!.inc()

        } catch (e: Exception) {
        }
    }

    fun onPlayAgain() {
        _back.value = true
    }

    fun onPlayAgainCompleted() {
        _back.value = false
    }

    fun onShare() {
        _share.value = true
    }

    fun onShareCompleted() {
        _share.value = false
    }
}
