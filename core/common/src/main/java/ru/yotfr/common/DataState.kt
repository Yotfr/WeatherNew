package ru.yotfr.common

sealed interface DataState<T: Any> {
    class Loading<T: Any> : DataState<T>
    class Success<T: Any>(val data: T) : DataState<T>
    class Exception<T: Any>(val cause: ExceptionCause) : DataState<T>
}

suspend fun <T: Any> DataState<T>.on(
    success: (suspend (T) -> Unit)?,
    loading: (suspend () -> Unit?)?,
    exception: (suspend (cause: ExceptionCause) -> Unit?)?
) : DataState<T> = apply {
    when(this) {
        is DataState.Loading<T> -> {
            loading?.let {
                loading()
            }
        }
        is DataState.Exception<T> -> {
            exception?.let {
                exception(cause)
            }
        }
        is DataState.Success<T> -> {
            success?.let {
                success(data)
            }
        }
    }
}

suspend fun <T : Any> DataState<T>.onSuccess(
    executable: suspend (T) -> Unit
): DataState<T> = apply {
    if (this is DataState.Success<T>) {
        executable(data)
    }
}

suspend fun <T : Any> DataState<T>.onLoading(
    executable: suspend () -> Unit
): DataState<T> = apply {
    if (this is DataState.Loading<T>) {
        executable()
    }
}

suspend fun <T : Any> DataState<T>.onException(
    executable: suspend (cause: ExceptionCause) -> Unit
): DataState<T> = apply {
    if (this is DataState.Exception<T>) {
        executable(cause)
    }
}