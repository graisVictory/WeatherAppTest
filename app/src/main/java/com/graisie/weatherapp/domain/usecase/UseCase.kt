package com.graisie.weatherapp.domain.usecase

interface UseCase<T, R> {

    suspend operator fun invoke(param: T): R
}
