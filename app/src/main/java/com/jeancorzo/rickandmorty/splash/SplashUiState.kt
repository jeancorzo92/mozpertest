package com.jeancorzo.rickandmorty.splash

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object NavigateToLogin : SplashUiState
    data object NavigateToHome : SplashUiState
}