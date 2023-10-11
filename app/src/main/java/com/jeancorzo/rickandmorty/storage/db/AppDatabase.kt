package com.jeancorzo.rickandmorty.storage.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jeancorzo.rickandmorty.storage.db.dao.CharacterDao
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

val MIGRATION_2_3 = object: Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS EpisodeEntity")
    }
}