package com.jeancorzo.rickandmorty.repository

interface DataMapper<in T, out S> {
    fun map(source: T): S
}