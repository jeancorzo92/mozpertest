package com.jeancorzo.rickandmorty.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.session.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(sessionManager: SessionManager) : ViewModel() {

    private val mUiState = MutableLiveData<SplashUiState>(SplashUiState.Loading)
    val uiState: LiveData<SplashUiState> = mUiState

    init {
        viewModelScope.launch {
            delay(2000)
            mUiState.value = if (sessionManager.isLoggedIn()) {
                SplashUiState.NavigateToHome
            } else {
                SplashUiState.NavigateToLogin
            }
        }
    }

}