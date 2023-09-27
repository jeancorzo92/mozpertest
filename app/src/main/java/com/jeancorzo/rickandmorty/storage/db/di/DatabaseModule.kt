package com.jeancorzo.rickandmorty.storage.db.di

import androidx.room.Room
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}