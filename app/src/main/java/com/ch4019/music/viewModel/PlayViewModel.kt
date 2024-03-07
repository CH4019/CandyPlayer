package com.ch4019.music.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayViewModel : ViewModel(

){
    private val _playState = MutableStateFlow(PlayState())
    val isPlaying = _playState.asStateFlow()

    init {
        initPlayState()
    }
    private fun initPlayState() {
        viewModelScope.launch(Dispatchers.IO) {
            _playState.update {
                it.copy(
                    playState = false
                )
            }
        }
    }

    fun changePlayState() {
        viewModelScope.launch(Dispatchers.IO) {
            _playState.update {
                it.copy(
                    playState = !it.playState
                )
            }
        }
    }



}

data class PlayState(
    val playState: Boolean = false
)