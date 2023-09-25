package com.jeancorzo.rickandmorty.session

interface SessionManager {
    suspend fun isLoggedIn(): Boolean
}