package ru.yotfr.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.yotfr.common.model.DataState
import ru.yotfr.common.model.ExceptionCause
import javax.inject.Inject

class LocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val appContext: Context
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): DataState<Location> {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission) {
            return DataState.Exception(
                cause = ExceptionCause.LocationPermissionsPermanentlyDisabled
            )
        }
        if (!isGpsEnabled) {
            return DataState.Exception(
                cause = ExceptionCause.GpsPermissionDisabled
            )
        }

        return suspendCancellableCoroutine { continuation ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        continuation.resume(
                            value = DataState.Success(
                                Location(
                                    latitude = result.latitude,
                                    longitude = result.longitude
                                )
                            ),
                            onCancellation = null
                        )
                    } else {
                        continuation.resume(
                            value = DataState.Exception(
                                cause = ExceptionCause.Unknown
                            ),
                            onCancellation = null
                        )
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { location ->
                    continuation.resume(
                        DataState.Success(
                            Location(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        ), null
                    )
                }
                addOnFailureListener {
                    continuation.resume(
                        value = DataState.Exception(
                            cause = ExceptionCause.Unknown
                        ),
                        onCancellation = null
                    )
                }
                addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }
}