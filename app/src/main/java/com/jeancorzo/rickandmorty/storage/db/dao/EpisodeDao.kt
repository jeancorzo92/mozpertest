package com.jeancorzo.rickandmorty.storage.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity

@Dao
interface EpisodeDao : InsertAllDao<EpisodeEntity> {

    @Query("SELECT * FROM episodeentity")
    fun getAllEpisodes(): PagingSource<Int, EpisodeEntity>

}