package com.jeancorzo.rickandmorty.characters.service.dto

import com.jeancorzo.rickandmorty.characters.service.dto.LocationDto
import com.jeancorzo.rickandmorty.characters.service.dto.OriginDto

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: LocationDto,
    val image: String,
    val episode: ArrayList<String>,
    val url: String,
    val created: String
)