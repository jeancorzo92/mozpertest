package com.jeancorzo.rickandmorty

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.jeancorzo.rickandmorty.characters.di.charactersModule
import com.jeancorzo.rickandmorty.di.appModule
import com.jeancorzo.rickandmorty.login.di.loginModule
import com.jeancorzo.rickandmorty.session.di.sessionModule
import com.jeancorzo.rickandmorty.splash.di.splashModule
import com.jeancorzo.rickandmorty.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RickAndMortyApplication)
            modules(
                appModule,
                splashModule,
                loginModule,
                sessionModule,
                storageModule,
                charactersModule
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .crossfade(true)
            .build()
    }
}