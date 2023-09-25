package com.jeancorzo.rickandmorty.splash

sealed interface SplashNavigationDirection {
    data object ToLogin : SplashNavigationDirection
    data object ToHome : SplashNavigationDirection
}