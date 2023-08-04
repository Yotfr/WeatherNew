package ru.yotfr.network.actualweather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.calladapter.NetworkResponseAdapterFactory
import ru.yotfr.network.actualweather.model.ActualWeatherDTO
import java.util.logging.Logger

interface ActualWeatherService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("hourly", encoded = true) hourly: String?,
        @Query("daily", encoded = true) daily: String?,
        @Query("timezone") timezone: String,
        @Query("forecast_days") forecastDays: Int
    ) : NetworkResponse<ActualWeatherDTO>
}

internal fun ActualWeatherService() : ActualWeatherService {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ActualWeatherService::class.java)
}