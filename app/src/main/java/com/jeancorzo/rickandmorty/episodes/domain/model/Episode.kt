package com.jeancorzo.rickandmorty.episodes.domain.model

data class Episode(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
