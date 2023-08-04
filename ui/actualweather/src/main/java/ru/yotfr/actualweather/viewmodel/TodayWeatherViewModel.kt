package ru.yotfr.actualweather.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.yotfr.actualweather.action.TodayWeatherAction
import ru.yotfr.actualweather.middleware.TodayWeatherNetworkingMiddleware
import ru.yotfr.actualweather.reducer.TodayWeatherReducer
import ru.yotfr.actualweather.state.TodayWeatherState
import ru.yotfr.actualweather.usecase.GetTodayWeatherUseCase
import ru.yotfr.shared.redux.LoggingMiddleware
import ru.yotfr.shared.redux.Store
import javax.inject.Inject

@HiltViewModel
class TodayWeatherViewModel @Inject constructor(
    private val getTodayWeatherUseCase: GetTodayWeatherUseCase
) : ViewModel() {
    private val store = Store(
        initialState = TodayWeatherState(),
        reducer = TodayWeatherReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            TodayWeatherNetworkingMiddleware(getTodayWeatherUseCase)
        )
    )

    val screenState: StateFlow<TodayWeatherState> = store.state

    fun openedScreen() {
        viewModelScope.launch {
            Log.d("TEST","DISPATCH")
            store.dispatch(TodayWeatherAction.OpenedScreen)
        }
    }

    fun selectedHourChanged(newIndex: Int) {
        viewModelScope.launch {
            store.dispatch(TodayWeatherAction.SelectedHourChanged(newIndex))
        }
    }
}