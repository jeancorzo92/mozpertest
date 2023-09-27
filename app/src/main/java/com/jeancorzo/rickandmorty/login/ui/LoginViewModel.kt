package com.jeancorzo.rickandmorty.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.session.SessionManager
import com.jeancorzo.rickandmorty.utils.CombineLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {

    val username = MutableLiveData("")
    val password = MutableLiveData("")
    val loginEnabled = CombineLiveData(username, password) { username, password ->
        validateCredentials(username, password)
    }
    private val mUiState = MutableLiveData<LoginUiState>(LoginUiState.WaitingUserInput)
    val uiState: LiveData<LoginUiState> = mUiState

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            mUiState.postValue(LoginUiState.Loading)
            sessionManager.logIn()
            mUiState.postValue(LoginUiState.LoginSuccess)
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        val isValidUsername = username.isNotBlank() && username.length > 3
        val isValidPassword = password.isNotBlank() && password.length > 3
        return isValidUsername && isValidPassword
    }
}