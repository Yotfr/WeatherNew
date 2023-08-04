package ru.yotfr.common.model

sealed interface ExceptionCause {
    object Unknown : ExceptionCause
    object NoHomeOrFavoritePlace: ExceptionCause
    object LocationPermissionsPermanentlyDisabled: ExceptionCause
    object GpsPermissionDisabled: ExceptionCause
}