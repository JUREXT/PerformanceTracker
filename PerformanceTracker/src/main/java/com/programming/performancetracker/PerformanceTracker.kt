package com.programming.performancetracker

import android.util.Log
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark
import kotlin.time.TimeSource

/**
 * PerformanceTracker is a lightweight utility for measuring the execution time of code blocks or named operations.
 * It is safe to use in both development and production (you can disable logging globally).
 */
@OptIn(ExperimentalTime::class)
object PerformanceTracker {

    private const val TAG = "PerformanceTracker"

    private val startMarks = mutableMapOf<String, TimeMark>()
    private val measuredDurations = mutableMapOf<String, Duration>()

    private var isEnabled: Boolean = true
    private var tag: String = TAG

    /**
     * Custom logging function.
     * Defaults to Android Logcat with [TAG].
     */
    private var logger: ((tag: String, message: String) -> Unit)? = { t, msg ->
        Log.d(t, msg)
    }

    fun enabled(enabled: Boolean) {
        this.isEnabled = enabled
    }

    fun setTag(tag: String) {
        this.tag = tag
    }

    fun getTag(): String = this.tag

    /** Start timing for a given [label] */
    @OptIn(ExperimentalTime::class)
    fun startTracking(label: String) {
        if (!isEnabled) return
        startMarks[label] = TimeSource.Monotonic.markNow()
        logger?.invoke(TAG, "Started tracking [$label]")
    }

    /** Stop timing for a given [label] and record the duration */
    fun stopTracking(label: String) {
        if (!isEnabled) return

        val mark = startMarks[label]
        if (mark != null) {
            val duration = mark.elapsedNow()
            measuredDurations[label] = duration
            logger?.invoke(TAG, "[$label] completed in ${duration.inWholeMilliseconds}")
        } else {
            logger?.invoke(TAG, "No start time recorded for [$label]")
        }
    }

    /** Get the last recorded duration for a given [label] */
    fun getDurationForLabelOrNull(label: String): Duration? {
        return if (isEnabled) {
            val duration = measuredDurations[label]
            duration?.let {
                logger?.invoke(TAG, "[$label] completed in ${duration.inWholeMilliseconds} whole milliseconds")
            }
            duration
        } else {
            null
        }
    }

    /** Log all recorded durations */
    fun logAllDurations() {
        if (!isEnabled) return
        measuredDurations.forEach { (label, duration) ->
            logger?.invoke(TAG, "[$label] took ${duration.inWholeMilliseconds}")
        }
    }

    /** Reset all stored start times and durations */
    fun reset() {
        startMarks.clear()
        measuredDurations.clear()
        logger?.invoke(TAG, "Timers reset")
    }

    /**
     * Convenience method to automatically measure the time of a code block.
     * Example:
     * ```
     * PerformanceTracker.measure("load_data") {
     *     loadData()
     * }
     * ```
     */
    inline fun <T> measure(label: String, block: () -> T): T {
        startTracking(label)
        val result = block()
        stopTracking(label)
        return result
    }
}