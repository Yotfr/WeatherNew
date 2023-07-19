package ru.yotfr.network.calladapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class NetworkResponseCall<T : Any>(
    private val proxy: Call<T>
) : Call<NetworkResponse<T>> {

    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val message = response.message()
                if (response.isSuccessful && body != null) {
                    callback.onResponse(
                        this@NetworkResponseCall, Response.success(NetworkResponse.Success(body))
                    )
                } else {
                    callback.onResponse(
                        this@NetworkResponseCall, Response.success(
                            NetworkResponse.Error(
                                code = code, message = message
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = NetworkResponse.Exception<T>(t)
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResult))
            }
        })
    }

    override fun execute(): Response<NetworkResponse<T>> = throw NotImplementedError()
    override fun clone(): Call<NetworkResponse<T>> = NetworkResponseCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() { proxy.cancel() }
}