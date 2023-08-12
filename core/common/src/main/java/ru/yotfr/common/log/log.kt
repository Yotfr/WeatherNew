package ru.yotfr.common.log

object log {
    private val caller: String
        get() = Thread.currentThread().stackTrace.drop(2).first {
            it.className.substringBeforeLast('.') !=
                    log::class.qualifiedName!!.substringBeforeLast('.')
        }.let { "${it.fileName}:${it.lineNumber}" }


}