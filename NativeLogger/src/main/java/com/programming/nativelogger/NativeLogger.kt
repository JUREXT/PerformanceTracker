package com.programming.nativelogger
import android.util.Log

/**
 * NativeLogger - A lightweight logging utility that wraps Android's Log methods.
 * Can be toggled globally using [initLogger].
 *
 * Usage:
 *     d { "User logged in: $userId" }
 *     d(e) { "Login failed for $userId" }
 *     i { "Fetching data..." }
 *     w { "This might cause issues" }
 *     e { "Something went wrong" }
 */
object NativeLogger {

    const val DEFAULT_TAG = "NativeLogger"

    var isLoggingEnabled: Boolean = true
        private set

    @JvmStatic
    fun initLogger(enabled: Boolean) {
        isLoggingEnabled = enabled
    }

    // DEBUG
    inline fun d(tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.d(tag, message())
    }

    inline fun d(error: Throwable, tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.d(tag, message(), error)
    }

    // INFO
    inline fun i(tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.i(tag, message())
    }

    inline fun i(error: Throwable, tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.i(tag, message(), error)
    }

    // WARNING
    inline fun w(tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.w(tag, message())
    }

    inline fun w(error: Throwable, tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.w(tag, message(), error)
    }

    // ERROR
    inline fun e(tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.e(tag, message())
    }

    inline fun e(error: Throwable, tag: String = DEFAULT_TAG, message: () -> String) {
        if (isLoggingEnabled) Log.e(tag, message(), error)
    }
}
