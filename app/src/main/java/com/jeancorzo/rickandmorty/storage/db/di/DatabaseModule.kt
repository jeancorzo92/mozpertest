package com.jeancorzo.rickandmorty.storage.db.di

import androidx.room.Room
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import com.jeancorzo.rickandmorty.storage.db.MIGRATION_2_3
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(MIGRATION_2_3)
            .build()
    }
}