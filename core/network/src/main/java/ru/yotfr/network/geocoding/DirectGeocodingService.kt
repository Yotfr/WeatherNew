package ru.yotfr.network.geocoding

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.calladapter.NetworkResponseAdapterFactory
import ru.yotfr.network.geocoding.model.PlaceDTO
import ru.yotfr.network.weather.API_KEY

interface DirectGeocodingService {
    @GET("geo/1.0/direct")
    suspend fun searchPlaces(
        @Query("q") query: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("limit") limit: Int = 5
    ) : NetworkResponse<List<PlaceDTO>>
}

internal fun DirectGeocodingService() : DirectGeocodingService {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(DirectGeocodingService::class.java)
}