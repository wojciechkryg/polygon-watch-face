package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import android.graphics.Path
import com.wojdor.common.domain.Point
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BaseWatchFaceTest {

    @MockK
    private lateinit var canvas: Canvas

    @MockK
    private lateinit var path: Path

    private lateinit var watchFace: BaseWatchFace

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        watchFace = BaseWatchFace(path)
        watchFace.backgroundColor = BACKGROUND_COLOR
    }

    @Test
    fun `When background is drew then it is drew it with proper color`() {
        watchFace.drawBackground(canvas)

        verify { canvas.drawColor(BACKGROUND_COLOR) }
    }

    @Test
    fun `When 0 digit is drew then nothing is called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 0)

        verify(exactly = 0) {
            canvas.drawCircle(any(), any(), any(), any())
            canvas.drawPath(any(), any())
        }
    }

    @Test
    fun `When 1 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 1)

        verify {
            canvas.drawCircle(0F, 0F, DOT_RADIUS, any())
        }
    }

    @Test
    fun `When 2 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 2)

        verify {
            path.addRoundRect(
                -1.1904762F,
                13.095238F,
                1.1904762F,
                -13.095238F,
                1.1904762F,
                1.1904762F,
                Path.Direction.CW
            )
        }
    }

    @Test
    fun `When 3 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 3)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(12.371792F, 7.142857F)
                lineTo(-12.371792F, 7.142857F)
                lineTo(-3.149976E-14F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 4 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 4)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(14.285714F, -3.4989908E-15F)
                lineTo(4.3737385E-15F, 14.285714F)
                lineTo(-14.285714F, 5.2484863E-15F)
                lineTo(-6.1232338E-15F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 5 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 5)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(13.586521F, -4.4145284F)
                lineTo(8.396932F, 11.557385F)
                lineTo(-8.396932F, 11.557385F)
                lineTo(-13.586521F, -4.4145284F)
                lineTo(-6.1232338E-15F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 6 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 6)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(12.371792F, -7.142857F)
                lineTo(12.371792F, 7.142857F)
                lineTo(1.7062001E-14F, 14.285714F)
                lineTo(-12.371792F, 7.142857F)
                lineTo(-12.371792F, -7.142857F)
                lineTo(-6.1232338E-15F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 7 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 7)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(11.169021F, -8.906997F)
                lineTo(13.927542F, 3.1788704F)
                lineTo(6.198339F, 12.870984F)
                lineTo(-6.198339F, 12.870984F)
                lineTo(-13.927542F, 3.1788704F)
                lineTo(-11.169021F, -8.906997F)
                lineTo(-6.1232338E-15F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 8 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 8)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(10.101525F, -10.101525F)
                lineTo(14.285714F, -3.4989908E-15F)
                lineTo(10.101525F, 10.101525F)
                lineTo(4.3737385E-15F, 14.285714F)
                lineTo(-10.101525F, 10.101525F)
                lineTo(-14.285714F, 5.2484863E-15F)
                lineTo(-10.101525F, -10.101525F)
                lineTo(1.9253293E-14F, -14.285714F)
            }
        }
    }

    @Test
    fun `When 9 digit is drew then proper methods are called`() {
        watchFace.drawDigit(canvas, CENTER_POINT, DOT_RADIUS, DIGIT_RADIUS, 9)

        verify {
            with(path) {
                moveTo(-2.6242432E-15F, -14.285714F)
                lineTo(9.18268F, -10.943492F)
                lineTo(14.068682F, -2.4806883F)
                lineTo(12.371792F, 7.142857F)
                lineTo(4.886002F, 13.42418F)
                lineTo(-4.886002F, 13.42418F)
                lineTo(-12.371792F, 7.142857F)
                lineTo(-14.068682F, -2.4806883F)
                lineTo(-9.18268F, -10.943492F)
                lineTo(-5.6876284E-14F, -14.285714F)
            }
        }
    }

    companion object {
        private const val BACKGROUND_COLOR = 3
        private val CENTER_POINT = Point()
        private const val DOT_RADIUS = 2.3809524F
        private const val DIGIT_RADIUS = 14.285714F
    }
}