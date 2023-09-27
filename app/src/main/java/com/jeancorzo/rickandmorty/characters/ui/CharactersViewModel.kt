package com.jeancorzo.rickandmorty.characters.ui

import androidx.lifecycle.ViewModel
import com.jeancorzo.rickandmorty.characters.repository.CharacterRepositoryAPI

class CharactersViewModel(
    characterRepository: CharacterRepositoryAPI
) : ViewModel() {

    val characterList = characterRepository.getCharacterList()

}