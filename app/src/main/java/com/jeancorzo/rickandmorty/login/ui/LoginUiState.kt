package com.jeancorzo.rickandmorty.login.ui

sealed interface LoginUiState {
    data object WaitingUserInput : LoginUiState
    data object Loading : LoginUiState
    data object LoginSuccess : LoginUiState
}