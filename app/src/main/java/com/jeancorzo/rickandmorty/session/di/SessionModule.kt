package com.jeancorzo.rickandmorty.session.di

import com.jeancorzo.rickandmorty.session.LocalSessionManager
import com.jeancorzo.rickandmorty.session.SessionManager
import org.koin.dsl.module

val sessionModule = module {
    factory<SessionManager> { LocalSessionManager(get()) }
}