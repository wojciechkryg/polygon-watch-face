package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import com.wojdor.common.domain.Point
import com.wojdor.common.domain.WatchFaceTime
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.util.*

class BasicWatchFaceTest {

    @MockK
    private lateinit var canvas: Canvas

    @SpyK
    private var watchFace = BasicWatchFace()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        watchFace.init(WATCH_SIZE, WATCH_SIZE)
    }

    @Test
    fun `When watch face is drew then it calls proper methods to draw digits`() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 34)
        }

        watchFace.drawWatchFace(canvas, WatchFaceTime(calendar, true))

        verify {
            watchFace.drawDigit(canvas, Point(30F, 30F), DOT_RADIUS, DIGIT_RADIUS, 1)
            watchFace.drawDigit(canvas, Point(70F, 30F), DOT_RADIUS, DIGIT_RADIUS, 2)
            watchFace.drawDigit(canvas, Point(30F, 70F), DOT_RADIUS, DIGIT_RADIUS, 3)
            watchFace.drawDigit(canvas, Point(70F, 70F), DOT_RADIUS, DIGIT_RADIUS, 4)
        }
    }

    companion object {
        private const val WATCH_SIZE = 100
        private const val DOT_RADIUS = 2.3809524F
        private const val DIGIT_RADIUS = 14.285714F
    }
}