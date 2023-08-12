package ru.yotfr.actualweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.yotfr.actualweather.action.WeeklyWeatherAction
import ru.yotfr.actualweather.middleware.WeeklyWeatherNetworkingMiddleware
import ru.yotfr.actualweather.reducer.WeeklyWeatherReducer
import ru.yotfr.actualweather.state.WeeklyWeatherState
import ru.yotfr.actualweather.usecase.GetWeeklyWeatherUseCase
import ru.yotfr.shared.redux.LoggingMiddleware
import ru.yotfr.shared.redux.Store
import javax.inject.Inject

@HiltViewModel
class WeeklyWeatherViewModel @Inject constructor(
    private val getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
) : ViewModel() {
    private val store = Store(
        initialState = WeeklyWeatherState(),
        reducer = WeeklyWeatherReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            WeeklyWeatherNetworkingMiddleware(getWeeklyWeatherUseCase)
        )
    )

    val screenState: StateFlow<WeeklyWeatherState> = store.state

    fun openedScreen() {
        viewModelScope.launch {
            store.dispatch(WeeklyWeatherAction.OpenedScreen)
        }
    }

    fun selectedHourChanged(newIndex: Int) {
        viewModelScope.launch {
            store.dispatch(WeeklyWeatherAction.SelectedDayChanged(newIndex))
        }
    }
}