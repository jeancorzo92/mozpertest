package com.jeancorzo.rickandmorty.login

sealed interface LoginUiState {
    data object WaitingUserInput : LoginUiState
    data object Loading : LoginUiState
    data object LoginSuccess : LoginUiState
}