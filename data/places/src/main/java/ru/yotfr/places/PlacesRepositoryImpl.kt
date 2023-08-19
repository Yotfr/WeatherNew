package ru.yotfr.places

import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.network.geocoding.GeocodingRemoteProvider
import ru.yotfr.places.mapper.mapToPlaceModel
import ru.yotfr.places.repository.PlacesRepository
import ru.yotfr.common.DataState
import ru.yotfr.common.ExceptionCause
import ru.yotfr.common.log.log
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.places.mapper.mapToPlaceEntity
import ru.yotfr.shared.model.PlaceModel
import java.util.Locale
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val geocodingRemoteProvider: GeocodingRemoteProvider,
    private val placesLocalProvider: PlacesLocalProvider
) : PlacesRepository {
    override suspend fun getPlace(placeId: Long): DataState<PlaceModel> {
        val localCache = placesLocalProvider.getPlace(id = placeId).mapToPlaceModel()
        return DataState.Success(data = localCache)
    }

    override suspend fun searchPlaces(query: String): DataState<List<PlaceModel>> {
        val networkResponse = geocodingRemoteProvider.getPlaces(
            name = query,
            count = 10,
            lang = Locale.getDefault().language
        )
        log.i("Received searched places response from remote api")
        return when(networkResponse) {
            is NetworkResponse.Success -> {
                log.d("Received today weather response from remote api is Success. Data: ${networkResponse.data}")
                val mappedNetworkResponse = networkResponse.data.results.map { it.mapToPlaceModel(selected = false, home = false) }
                DataState.Success(
                    data = mappedNetworkResponse
                )
            }
            is NetworkResponse.Error -> {
                log.d("Received searched places response from remote api is Exception. Error: ${networkResponse.message}")
                DataState.Exception(ExceptionCause.Unknown)
            }
            is NetworkResponse.Exception -> {
                log.d("Received searched places response from remote api is Error. Exception: ${networkResponse.e}")
                DataState.Exception(ExceptionCause.Unknown)
            }
        }
    }

    override suspend fun addPlace(placeModel: PlaceModel) {
        placesLocalProvider.upsertPlace(placeModel.mapToPlaceEntity())
    }

    override suspend fun selectPlace(placeId: Long) {
        placesLocalProvider.getSelectedPlace()?.let {
            placesLocalProvider.upsertPlace(it.copy(isSelected = false))
        }
        placesLocalProvider.getPlace(placeId).let {
            placesLocalProvider.upsertPlace(it.copy(isSelected = true))
        }
    }

    override suspend fun setPlaceAsHome(placeId: Long) {
        placesLocalProvider.getHomePlace()?.let {
            placesLocalProvider.upsertPlace(it.copy(isHome = false))
        }
        placesLocalProvider.getPlace(placeId).let {
            placesLocalProvider.upsertPlace(it.copy(isHome = true))
        }
    }

}