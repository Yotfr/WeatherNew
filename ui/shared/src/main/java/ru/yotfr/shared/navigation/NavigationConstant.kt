package ru.yotfr.shared.navigation

object ActualWeatherNavigationConstant {
    const val nestedRoute = "actual_weather_nested_route"
    const val todayScreenRoute = "actual_weather_today_screen_nested_route"
}

object HistoricalWeatherNavigationConstant {
    const val nestedRoute = "historical_weather_nested_route"
}

object MarineWeatherNavigationConstant {
    const val nestedRoute = "marine_weather_nested_route"
}

object AirQualityNavigationConstant {
    const val nestedRoute = "air_quality_nested_route"
}

sealed class NavBarItems(val route: String) {
    object ActualWeather : NavBarItems(ActualWeatherNavigationConstant.nestedRoute)
    object HistoricalWeather : NavBarItems(HistoricalWeatherNavigationConstant.nestedRoute)
    object MarineWeather : NavBarItems(HistoricalWeatherNavigationConstant.nestedRoute)
    object AirQuality : NavBarItems(HistoricalWeatherNavigationConstant.nestedRoute)
}

val navBarItems = listOf(
    NavBarItems.ActualWeather,
    NavBarItems.HistoricalWeather,
    NavBarItems.MarineWeather,
    NavBarItems.AirQuality
)