package ru.yotfr.shared.repository

import ru.yotfr.common.model.DataState
import ru.yotfr.shared.model.PlaceModel

interface SharedRepository {
    suspend fun getSelectedPlace(): DataState<PlaceModel>
}