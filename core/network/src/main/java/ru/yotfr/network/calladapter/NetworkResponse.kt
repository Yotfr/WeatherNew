package ru.yotfr.network.calladapter

sealed interface NetworkResponse<T: Any> {
    /**
     * [NetworkResponse.Success] Represents a network result that successfully received a response containing body data.
     */
    class Success<T: Any>(val data: T) : NetworkResponse<T>
    /**
     * [NetworkResponse.Error] Represents a network result that successfully received a response containing an error message.
     */
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResponse<T>
    /**
     * [NetworkResponse.Exception] Represents a network result that faced an unexpected exception before getting a response from the network such as IOException and UnKnownHostException.
     */
    class Exception<T: Any>(val e: Throwable) : NetworkResponse<T>
}

suspend fun <T: Any> NetworkResponse<T>.on(
    success: (suspend (T) -> Unit)?,
    error: (suspend (code: Int, message: String?) -> Unit?)?,
    exception: (suspend (e: Throwable) -> Unit?)?
) : NetworkResponse<T> = apply {
    when(this) {
        is NetworkResponse.Error<T> -> {
            error?.let {
                error(code, message)
            }
        }
        is NetworkResponse.Exception<T> -> {
            exception?.let {
                exception(e)
            }
        }
        is NetworkResponse.Success<T> -> {
            success?.let {
                success(data)
            }
        }
    }
}

suspend fun <T : Any> NetworkResponse<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResponse<T> = apply {
    if (this is NetworkResponse.Success<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResponse<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResponse<T> = apply {
    if (this is NetworkResponse.Error<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResponse<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResponse<T> = apply {
    if (this is NetworkResponse.Exception<T>) {
        executable(e)
    }
}