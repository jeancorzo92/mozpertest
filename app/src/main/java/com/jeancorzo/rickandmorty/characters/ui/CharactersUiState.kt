package com.jeancorzo.rickandmorty.characters.ui

import com.jeancorzo.rickandmorty.characters.model.Character

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data object ErrorLoadingCharacters : CharactersUiState
    data class ShowCharacters(val characterList: List<Character>) : CharactersUiState
}