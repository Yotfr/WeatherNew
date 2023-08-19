package ru.yotfr.common.log

import android.util.Log
object log {

    private const val perStringCharLimit = 2000
    private val caller: String
        get() = Thread.currentThread().stackTrace.drop(2).first {
            it.className.substringBeforeLast('.') !=
                    log::class.qualifiedName!!.substringBeforeLast('.')
        }.let { "${it.fileName}:${it.lineNumber}" }

    private fun log(
        message: String,
        level: Int,
        throwable: Throwable?
    ) {
        if (message.length < perStringCharLimit) {
            Log.println(
                level, caller, message
            )
        }
        val sections = message.length / perStringCharLimit
        for (i in 0..sections) {
            val max = perStringCharLimit * (i + 1)
            if (max >= message.length) {
                Log.println(
                    level, caller, message.substring(perStringCharLimit * i)
                )
            } else {
                Log.println(
                    level, caller, message.substring(perStringCharLimit * i, max)
                )
            }
        }
    }

    /**
     * Detaield
     */
    fun v(
        msg: String,
        thr: Throwable? = null,
    ) {
        log(msg, Log.VERBOSE, thr)
    }

    /**
     * Debugging
     */
    fun d(
        msg: String,
        thr: Throwable? = null,
    ) {
        log(msg, Log.DEBUG, thr)
    }

    /**
     * Information, success
     */
    fun i(
        msg: String,
        thr: Throwable? = null,
    ) {
        log(msg, Log.INFO, thr)
    }

    /**
     * Completely unexpected, but not necessarily an error
     */
    fun w(
        msg: String,
        thr: Throwable? = null,
    ) {
        log(msg, Log.WARN, thr)
    }

    /**
     * Error
     */
    fun e(
        msg: String,
        thr: Throwable? = null,
    ) {
        log(msg, Log.ERROR, thr)
    }

    /**
     *  If smth goes absolutely wrong
     */
    fun wtf(
        msg: String,
        thr: Throwable? = null,
    ) {
        Log.wtf(caller,msg)
    }

}