package com.jeancorzo.rickandmorty.episodes.service.dto

data class EpisodeDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)