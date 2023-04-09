package com.graisie.weatherapp.data.city.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.graisie.weatherapp.R
import com.graisie.weatherapp.data.city.model.CityApiEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.collections.ArrayList

class CitiesApiImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) : CitiesApi {

    private var cached: List<CityApiEntity> = emptyList()

    override suspend fun getCities(searchQuery: String?): List<CityApiEntity> {
        throw java.lang.IllegalArgumentException("smth")
        return cached.ifEmpty {
            withContext(Dispatchers.IO) {
                context.resources.openRawResource(R.raw.city_list)
                    .reader()
                    .let<InputStreamReader, ArrayList<CityApiEntity>?> {
                        gson.fromJson(it, object : TypeToken<ArrayList<CityApiEntity>>() {}.type)
                    }
                    ?.apply { cached = this }
            }
        }.let { cities ->
            if (searchQuery.isNullOrEmpty()) {
                cities.orEmpty()
            } else {
                cities.orEmpty().filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }
}
