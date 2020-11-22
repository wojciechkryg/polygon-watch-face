package com.wojdor.common.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class WatchFaceTimeTest {

    private lateinit var calendar: Calendar
    private lateinit var time: WatchFaceTime

    @Before
    fun setup() {
        calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 34)
            set(Calendar.SECOND, 56)
        }
        time = WatchFaceTime(calendar, false)
    }

    @Test
    fun `When time values are obtained then it returns proper values for each measure`() {
        assertEquals(0, time.analogHours)
        assertEquals(12, time.digitalHours)
        assertEquals(34, time.minutes)
        assertEquals(56, time.seconds)
        assertEquals(1, time.hoursFirstDigit)
        assertEquals(2, time.hoursSecondDigit)
        assertEquals(3, time.minutesFirstDigit)
        assertEquals(4, time.minutesSecondDigit)
        assertEquals(5, time.secondsFirstDigit)
        assertEquals(6, time.secondsSecondDigit)
    }

    @Test
    fun `When hour values are obtained in 24 hour format then it returns proper hours`() {
        calendar.set(Calendar.HOUR_OF_DAY, 15)
        time = WatchFaceTime(calendar, true)

        assertEquals(3, time.analogHours)
        assertEquals(15, time.digitalHours)
    }
}