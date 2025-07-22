package com.programming.performancetracker

import android.util.Log
import com.programming.performancetracker.PerformanceTracker.TAG


/**
 * PerformanceTracker is a lightweight utility for measuring the execution time of code blocks or named operations.
 * It is safe to use in both development and production (you can disable logging globally).
 */
object PerformanceTracker {

    private const val TAG = "PerformanceTracker"

    private val startTimestamps = mutableMapOf<String, Long>()
    private val measuredDurations = mutableMapOf<String, Long>()

    private var isEnabled: Boolean = true

    /**
     * Custom logging function.
     * Defaults to Android Logcat with [TAG].
     */
    var logger: ((tag: String, message: String) -> Unit)? = { t, msg ->
        Log.d(t, msg)
    }

    fun enabled(enabled: Boolean) {
        this.isEnabled = enabled
    }

    /** Start timing for a given [label] */
    fun trackStart(label: String) {
        if (!isEnabled) return
        startTimestamps[label] = System.currentTimeMillis()
        logger?.invoke(TAG, "Started tracking [$label]")
    }

    /** Stop timing for a given [label] and record the duration */
    fun trackStop(label: String) {
        if (!isEnabled) return

        val start = startTimestamps[label]
        if (start != null) {
            val duration = System.currentTimeMillis() - start
            measuredDurations[label] = duration
            logger?.invoke(TAG, "[$label] completed in ${duration}ms")
        } else {
            logger?.invoke(TAG, "No start time recorded for [$label]")
        }
    }

    /** Get the last recorded duration for a given [label] in milliseconds */
    fun getDuration(label: String): Long? {
        return if (isEnabled) measuredDurations[label] else null
    }

    /** Log all recorded durations */
    fun logAllDurations() {
        if (!isEnabled) return
        measuredDurations.forEach { (label, duration) ->
            logger?.invoke(TAG, "[$label] took ${duration}ms")
        }
    }

    /** Reset all stored start times and durations */
    fun reset() {
        startTimestamps.clear()
        measuredDurations.clear()
        logger?.invoke(TAG, "Timers reset")
    }

    /**
     * Convenience method to automatically measure the time of a code block.
     * Example:
     * ```
     * PerformanceTimer.measure("load_data") {
     *     loadData()
     * }
     * ```
     */
    inline fun <T> measure(label: String, block: () -> T): T {
        trackStart(label)
        val result = block()
        trackStop(label)
        return result
    }
}