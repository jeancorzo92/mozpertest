package com.jeancorzo.rickandmorty.characters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepositoryAPI
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data object ErrorLoadingCharacters : CharactersUiState
    data class DisplayCharacters(val characterList: List<Character>) : CharactersUiState
    data class AddCharacters(val characterList: List<Character>) : CharactersUiState
}

class CharactersViewModel(
    private val charactersRepositoryAPI: CharactersRepositoryAPI
) : ViewModel() {

    private val mUiState = MutableSharedFlow<CharactersUiState>()
    val uiState: SharedFlow<CharactersUiState> = mUiState

    init {
        viewModelScope.launch {
            delay(30)
            mUiState.emit(CharactersUiState.Loading)
            val characters = charactersRepositoryAPI.getCharacterList()
            mUiState.emit(CharactersUiState.DisplayCharacters(characters))
            delay(2000)
            mUiState.emit(CharactersUiState.AddCharacters(characters))
        }
    }
}