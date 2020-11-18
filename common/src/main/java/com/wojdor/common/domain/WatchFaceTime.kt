package com.wojdor.common.domain

import java.util.*

data class WatchFaceTime(private val calendar: Calendar, private val is24HourFormat: Boolean) {

    val digitalHours
        get() = if (is24HourFormat) {
            calendar[Calendar.HOUR_OF_DAY]
        } else {
            val hour = calendar[Calendar.HOUR]
            if (hour == 0) 12 else hour
        }
    val analogHours get() = calendar[Calendar.HOUR]
    val minutes get() = calendar[Calendar.MINUTE]
    val seconds get() = calendar[Calendar.SECOND]
    val milliseconds get() = calendar[Calendar.MILLISECOND]

    val hoursFirstDigit get() = digitalHours / 10
    val hoursSecondDigit get() = digitalHours % 10
    val minutesFirstDigit get() = minutes / 10
    val minutesSecondDigit get() = minutes % 10
    val secondsFirstDigit get() = seconds / 10
    val secondsSecondDigit get() = seconds % 10
}