package ru.yotfr.location

import ru.yotfr.common.DataState
import javax.inject.Inject

class LocationProvider @Inject constructor(
    private val locationTracker: LocationTracker
) {
    suspend fun getLocation(): DataState<Location> {
        return locationTracker.getCurrentLocation()
    }
}