package ru.yotfr.geocoding.repository

import ru.yotfr.common.DataState
import ru.yotfr.common.ExceptionCause
import ru.yotfr.common.log.log
import ru.yotfr.database.geocoding.GeocodingLocalProvider
import ru.yotfr.geocoding.mapper.mapToPlaceEntity
import ru.yotfr.geocoding.mapper.mapToPlaceModel
import ru.yotfr.location.LocationProvider
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.geocoding.GeocodingRemoteProvider
import ru.yotfr.shared.model.PlaceModel
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocodingRemoteProvider: GeocodingRemoteProvider,
    private val geocodingLocalProvider: GeocodingLocalProvider,
    private val locationProvider: LocationProvider
) : GeocodingRepository {

    override suspend fun addPlace(placeModel: PlaceModel) {
        geocodingLocalProvider.addPlace(
            placeEntity = placeModel.mapToPlaceEntity()
        )
    }

    override suspend fun searchPlaces(query: String): DataState<List<PlaceModel>> {
        when (val placesResponse = geocodingRemoteProvider.getPlacesByQuery(
            query = query
        )) {
            is NetworkResponse.Error -> {
                return DataState.Exception(cause = ExceptionCause.Unknown)
            }

            is NetworkResponse.Exception -> {
                return DataState.Exception(cause = ExceptionCause.Unknown)
            }

            is NetworkResponse.Success -> {
                log.d("placesResponse ${placesResponse.data}")
                return DataState.Success(data = placesResponse.data.map {
                    it.mapToPlaceModel(
                        isLocation = false
                    )
                })
            }
        }
    }

    override suspend fun getPlaceById(placeId: Long): PlaceModel {
        return geocodingLocalProvider.getPlaceById(placeId = placeId).mapToPlaceModel()
    }

    override suspend fun getAllPlaces(): List<PlaceModel> {
        return geocodingLocalProvider.getPlaces().map { it.mapToPlaceModel() }
    }

    override suspend fun getHomePlace(): PlaceModel? {
        return geocodingLocalProvider.getHomePlace()?.mapToPlaceModel()
    }

    override suspend fun getLocationPlace(): DataState<PlaceModel> {
        when (val locationResponse = locationProvider.getLocation()) {
            is DataState.Exception -> {
                return DataState.Exception(cause = locationResponse.cause)
            }

            is DataState.Loading -> {
                //TODO
                throw IllegalArgumentException("LOADING STATE SHOULD NOT HAPPED")
            }

            is DataState.Success -> {
                //TODO: float double
                val networkResponse = geocodingRemoteProvider.getPlacesByCoordinates(
                    latitude = locationResponse.data.latitude.toFloat(),
                    longitude = locationResponse.data.longitude.toFloat()
                )
                when (networkResponse) {
                    is NetworkResponse.Error -> {
                        return DataState.Exception(cause = ExceptionCause.Unknown)
                    }

                    is NetworkResponse.Exception -> {
                        return DataState.Exception(cause = ExceptionCause.Unknown)
                    }

                    is NetworkResponse.Success -> {
                        geocodingLocalProvider.updateLocationPlace(
                            placeEntity = networkResponse.data.first().mapToPlaceEntity(
                                isLocation = true
                            )
                        )
                        geocodingLocalProvider.getLocationPlace()?.mapToPlaceModel()
                            ?.let { placeModel ->
                                return DataState.Success(
                                    data = placeModel
                                )
                            } ?: kotlin.run {
                            return DataState.Exception(
                                cause = ExceptionCause.Unknown
                            )
                        }
                    }
                }
            }
        }
    }
}