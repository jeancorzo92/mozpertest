package com.jeancorzo.rickandmorty.splash.ui

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object NavigateToLogin : SplashUiState
    data object NavigateToHome : SplashUiState
}