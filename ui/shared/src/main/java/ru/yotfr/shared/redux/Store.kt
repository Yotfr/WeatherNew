package ru.yotfr.shared.redux


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * A [Store] is our state container for a given screen.
 *
 * @param[initialState] This is the initial state of the screen when it is first created.
 * @param[reducer] A system for taking in the current state, and a new action, and outputting the
 * updated state.
 * @param[middlewares] This is a list of [Middleware] entities for handling any side effects
 * for actions dispatched to this store.
 */
class Store<S: State, A: Action, E: Event>(
    initialState: S,
    private val reducer: Reducer<S, A, E>,
    private val middlewares: List<Middleware<S, A, E>> = emptyList()
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val currentState: S
        get() = _state.value

    suspend fun send(event: E) {
        _events.send(event)
    }

    suspend fun dispatch(action: A) {
        middlewares.forEach { middleware ->
            middleware.process(action, currentState, this)
        }

        reducer.reduce(currentState, action, this)
    }
}