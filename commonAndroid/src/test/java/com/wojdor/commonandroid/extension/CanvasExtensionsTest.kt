package com.wojdor.commonandroid.extension

import android.graphics.Canvas
import android.graphics.Paint
import com.wojdor.common.domain.Point
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CanvasExtensionsTest {

    @MockK
    private lateinit var paint: Paint

    @MockK
    private lateinit var canvas: Canvas

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `When canvas draw line extension is used then it call proper canvas draw line method`() {
        canvas.drawLine(Point(1F, 2F), Point(3F, 4F), paint)

        verify { canvas.drawLine(1F, 2F, 3F, 4F, paint) }
    }

    @Test
    fun `When canvas draw circle extension is used then it call proper canvas draw circle method`() {
        canvas.drawCircle(Point(1F, 2F), 1F, paint)

        verify { canvas.drawCircle(1F, 2F, 1F, paint) }
    }
}