package ru.yotfr.places.events

import ru.yotfr.shared.redux.Event

sealed class SearchPlacesEvent : Event {
    object NavigateBack : SearchPlacesEvent()
}