package com.jeancorzo.rickandmorty.service

interface ServiceGenerator {
    fun <T> createService(serviceClass: Class<T>): T
}