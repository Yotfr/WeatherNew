package ru.yotfr.network.weather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.calladapter.NetworkResponseAdapterFactory
import ru.yotfr.network.weather.model.CurrentWeatherDTO

const val API_KEY = "1eb0179a48894318d6e577a37302b28e"
interface CurrentWeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = API_KEY
    ) : NetworkResponse<CurrentWeatherDTO>
}
internal fun CurrentWeatherService() : CurrentWeatherService {
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
    return retrofit.create(CurrentWeatherService::class.java)
}