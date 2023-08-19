package ru.yotfr.shared.redux

interface Reducer<S: State, A: Action, E: Event> {
    /**
     * Given a [currentState] and some [action] that the user took, produce a new state
     */
    suspend fun reduce(currentState: S, action: A, store: Store<S, A, E>): S
}