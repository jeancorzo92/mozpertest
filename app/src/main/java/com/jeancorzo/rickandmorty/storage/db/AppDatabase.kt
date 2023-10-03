package com.jeancorzo.rickandmorty.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeancorzo.rickandmorty.storage.db.dao.CharacterDao
import com.jeancorzo.rickandmorty.storage.db.dao.EpisodeDao
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao
}