package ru.yotfr.actualweather.state

import ru.yotfr.shared.model.HourlyModel
import ru.yotfr.shared.redux.State

data class TodayWeatherState(
    val showProgressBar: Boolean = false,
    val selectedWeatherModel: HourlyModel? = null,
    val hourlyForecast: List<HourlyModel> = emptyList(),
    val errorMessage: String = ""
) : State