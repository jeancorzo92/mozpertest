package com.jeancorzo.rickandmorty.characters.service.dto

abstract class ListDto<T> (
    open val info: InfoDto,
    open val data: ArrayList<T>
)