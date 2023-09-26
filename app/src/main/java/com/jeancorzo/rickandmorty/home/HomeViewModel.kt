package com.jeancorzo.rickandmorty.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private val mNavigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = mNavigateToLogin

    private val mShowLogOutConfirmation = MutableLiveData<Unit>()
    val showLogOutConfirmation: LiveData<Unit> = mShowLogOutConfirmation

    fun logoutRequested() {
        mShowLogOutConfirmation.value = Unit
    }

    fun logOutConfirmed() {
        viewModelScope.launch(Dispatchers.IO) {
            sessionManager.logOut()
            mNavigateToLogin.postValue(Unit)
        }
    }
}