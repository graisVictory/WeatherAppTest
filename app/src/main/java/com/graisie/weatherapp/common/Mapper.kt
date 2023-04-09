package com.graisie.weatherapp.common

interface Mapper<T, R> {

    fun map(from: T): R
}
