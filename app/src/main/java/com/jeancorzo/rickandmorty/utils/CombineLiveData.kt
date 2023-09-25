package com.jeancorzo.rickandmorty.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class CombineLiveData<S1, S2, R>(
    private val source1: LiveData<S1>,
    private val source2: LiveData<S2>,
    private val combine: (S1, S2) -> R
) : MediatorLiveData<R>() {

    override fun onActive() {
        super.onActive()
        addSource(source1) { value ->
            val newValue = combine(value, source2.value ?: return@addSource)
            postValue(newValue)
        }
        addSource(source2) { value ->
            val newValue = combine(source1.value ?: return@addSource, value)
            postValue(newValue)
        }
    }

    override fun onInactive() {
        removeSource(source1)
        removeSource(source2)
        super.onInactive()
    }
}