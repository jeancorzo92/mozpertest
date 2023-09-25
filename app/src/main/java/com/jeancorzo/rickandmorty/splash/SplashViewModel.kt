package com.jeancorzo.rickandmorty.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.session.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(sessionManager: SessionManager) : ViewModel() {

    private val mNavigateToDirection = MutableLiveData<SplashNavigationDirection>()
    val navigateToDirection: LiveData<SplashNavigationDirection>
        get() = mNavigateToDirection

    init {
        viewModelScope.launch {
            delay(2000)
            mNavigateToDirection.value = if (sessionManager.isLoggedIn()) {
                SplashNavigationDirection.ToHome
            } else {
                SplashNavigationDirection.ToLogin
            }
        }
    }

}