package ru.yotfr.network.weather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.calladapter.NetworkResponseAdapterFactory
import ru.yotfr.network.weather.model.DailyWeatherDTO

interface DailyWeatherService {
    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = API_KEY
    ) : NetworkResponse<DailyWeatherDTO>
}
internal fun DailyWeatherService() : DailyWeatherService {
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
    return retrofit.create(DailyWeatherService::class.java)
}