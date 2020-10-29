package com.wojdor.commonandroid.extension

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CanvasExtensionsTest {

    @RelaxedMockK
    private lateinit var paint: Paint

    @RelaxedMockK
    private lateinit var canvas: Canvas

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `When canvas draw line extension is used then it call proper canvas draw line method`() {
        val startX = mockk<Float>(relaxed = true)
        val startY = mockk<Float>(relaxed = true)
        val endX = mockk<Float>(relaxed = true)
        val endY = mockk<Float>(relaxed = true)

        canvas.drawLine(PointF(startX, startY), PointF(endX, endY), paint)

        verify(exactly = 1) {
            canvas.drawLine(startX, startY, endX, endY, paint)
        }
    }

    @Test
    fun `When canvas draw circle extension is used then it call proper canvas draw circle method`() {
        val x = mockk<Float>(relaxed = true)
        val y = mockk<Float>(relaxed = true)

        canvas.drawCircle(PointF(x, y), 1F, paint)

        verify(exactly = 1) {
            canvas.drawCircle(x, y, 1F, paint)
        }
    }
}