package ru.yotfr.shared.redux

import ru.yotfr.common.log.log

/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat, so
 * that we can use this for debugging.
 */
class LoggingMiddleware<S: State, A: Action, E: Event> : Middleware<S, A, E> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A, E>) {
        log.d(
            "Processing action: $action; Current state: $currentState"
        )
    }
}