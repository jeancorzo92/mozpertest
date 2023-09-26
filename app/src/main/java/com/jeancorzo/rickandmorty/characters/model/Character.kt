package com.jeancorzo.rickandmorty.characters.model

data class Character(
    val id: Long = -1,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val imageUrl: String = "",
    val numberOfEpisodes: Int = 0
) {
    companion object
}

fun Character.Companion.rick(): Character  = Character(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "Male",
    "Earth",
    "Earth",
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    54
)
