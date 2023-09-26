package com.jeancorzo.rickandmorty.characters.service.dto

data class CharacterListDto(
    val info: InfoDto,
    val results: ArrayList<CharacterDto>
)

