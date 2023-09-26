package com.jeancorzo.rickandmorty.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.jeancorzo.rickandmorty.characters.model.Character

class CharactersViewModel(
    private val charactersPagingSource: PagingSource<Int, Character>
) : ViewModel() {

    val characterList = Pager(PagingConfig(20)) {
        charactersPagingSource
    }.flow.cachedIn(viewModelScope)

}