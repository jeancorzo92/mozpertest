package com.jeancorzo.rickandmorty.storage.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity

@Dao
interface CharacterDao : InsertAllDao<CharacterEntity> {

    @Query("SELECT * FROM characterentity")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

}