package ru.yotfr.shared.repository

import android.util.Log
import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.database.places.model.PlaceEntity
import ru.yotfr.location.LocationProvider
import ru.yotfr.shared.mapper.mapToPlaceModel
import ru.yotfr.common.DataState
import ru.yotfr.common.ExceptionCause
import ru.yotfr.shared.model.PlaceModel
import javax.inject.Inject

class SharedRepositoryImpl @Inject constructor(
    private val placesLocalProvider: PlacesLocalProvider,
    private val locationProvider: LocationProvider
) : SharedRepository {
    override suspend fun getSelectedPlace(): DataState<PlaceModel> {
        val selectedPlace = placesLocalProvider.getSelectedPlace()
        if (selectedPlace != null) {
            return DataState.Success(
                data = selectedPlace.mapToPlaceModel()
            )
        } else {
            val homePlace = placesLocalProvider.getHomePlace()
            if (homePlace != null) {
                return DataState.Success(
                    data = homePlace.mapToPlaceModel()
                )
            } else {
                val location = locationProvider.getLocation()
                Log.d("TEST","LOCATION $location")
                when(location) {
                    is DataState.Success -> {
                        Log.d("TEST","LOCATION success ${location.data}")
                        val newSelected = PlaceEntity(
                            id = 0,
                            admin1 = "",
                            country = "",
                            country_code = "",
                            elevation = 0.0,
                            feature_code = "",
                            latitude = location.data.latitude,
                            longitude = location.data.longitude,
                            name = "Location",
                            timezone = "",
                            isSelected = true,
                            isHome = false
                        )
                        placesLocalProvider.upsertPlace(newSelected)
                        return DataState.Success(
                            data = newSelected.mapToPlaceModel()
                        )
                    }
                    is DataState.Exception -> {
                        return DataState.Exception(
                            cause = ExceptionCause.Unknown
                        )
                    }
                    is DataState.Loading -> {
                        return DataState.Exception(
                            cause = ExceptionCause.Unknown
                        )
                    }
                }
            }
        }
    }
}