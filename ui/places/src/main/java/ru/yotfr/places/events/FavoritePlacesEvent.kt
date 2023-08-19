package ru.yotfr.places.events

import ru.yotfr.shared.redux.Event

sealed class FavoritePlacesEvent : Event {
    object NavigateBack : FavoritePlacesEvent()
}