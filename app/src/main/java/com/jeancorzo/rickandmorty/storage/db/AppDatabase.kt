package com.jeancorzo.rickandmorty.storage.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeancorzo.rickandmorty.storage.db.dao.CharacterDao
import com.jeancorzo.rickandmorty.storage.db.dao.EpisodeDao
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao
}