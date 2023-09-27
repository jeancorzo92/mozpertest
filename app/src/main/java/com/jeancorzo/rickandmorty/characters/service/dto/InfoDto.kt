package com.jeancorzo.rickandmorty.characters.service.dto

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)