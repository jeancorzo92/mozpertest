package com.jeancorzo.rickandmorty.characters.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val imageUrl: String,
    val numberOfEpisodes: Int = 0
) : Parcelable
