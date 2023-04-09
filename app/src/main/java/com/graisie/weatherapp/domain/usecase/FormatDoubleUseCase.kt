package com.graisie.weatherapp.domain.usecase

import javax.inject.Inject

class FormatDoubleUseCase @Inject constructor() : UseCase<Double, String> {

    override suspend fun invoke(param: Double): String {
        return String.format("%.1f", param)
    }
}
