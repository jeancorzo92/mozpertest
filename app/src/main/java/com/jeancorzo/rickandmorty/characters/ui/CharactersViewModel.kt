package com.jeancorzo.rickandmorty.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepositoryAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersRepositoryAPI: CharactersRepositoryAPI
) : ViewModel() {

    private val characterList = mutableListOf<Character>()

    private val mUiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = mUiState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepositoryAPI.getCharacterList().collect { characters ->
                characterList.addAll(characters)
                mUiState.emit(CharactersUiState.ShowCharacters(characters))
            }
        }
    }
}