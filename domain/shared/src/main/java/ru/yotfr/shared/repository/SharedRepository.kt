package ru.yotfr.shared.repository

import ru.yotfr.common.DataState
import ru.yotfr.shared.model.PlaceModel

interface SharedRepository {
    suspend fun getSelectedPlace(): DataState<PlaceModel>
}