package ru.yotfr.network.places

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.calladapter.NetworkResponseAdapterFactory
import ru.yotfr.network.places.model.PlacesDTO

interface PlacesService {
    @GET("v1/search")
    suspend fun searchPlaces(
        @Query("name") name: String,
        @Query("count") count: Int,
        @Query("language") lang: String
    ) : NetworkResponse<PlacesDTO>
}

internal fun PlacesService() : PlacesService {
    val okHttpClient = OkHttpClient.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/")
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(PlacesService::class.java)
}