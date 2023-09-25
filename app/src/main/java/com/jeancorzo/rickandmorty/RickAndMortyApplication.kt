package com.jeancorzo.rickandmorty

import android.app.Application
import com.jeancorzo.rickandmorty.di.viewModelModule
import com.jeancorzo.rickandmorty.session.di.sessionModule
import com.jeancorzo.rickandmorty.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RickAndMortyApplication)
            modules(viewModelModule, sessionModule, storageModule)
        }
    }
}