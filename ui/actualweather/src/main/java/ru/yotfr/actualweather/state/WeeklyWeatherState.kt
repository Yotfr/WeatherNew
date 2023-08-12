package ru.yotfr.actualweather.state

import ru.yotfr.shared.model.DailyModel
import ru.yotfr.shared.redux.State

data class WeeklyWeatherState(
    val showProgressBar: Boolean = false,
    val selectedWeatherModel: DailyModel? = null,
    val dailyForecast: List<DailyModel> = emptyList(),
    val errorMessage: String = ""
) : State
