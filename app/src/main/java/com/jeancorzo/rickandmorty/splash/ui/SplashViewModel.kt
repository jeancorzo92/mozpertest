package com.jeancorzo.rickandmorty.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val mUiState = MutableLiveData<SplashUiState>(SplashUiState.Loading)
    val uiState: LiveData<SplashUiState> = mUiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            checkSession()
        }
    }

    private suspend fun checkSession() {
        mUiState.postValue(if (sessionManager.isLoggedIn()) {
            SplashUiState.NavigateToHome
        } else {
            SplashUiState.NavigateToLogin
        })
    }

}