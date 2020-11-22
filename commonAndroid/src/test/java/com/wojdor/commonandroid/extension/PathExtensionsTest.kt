package com.wojdor.commonandroid.extension

import android.graphics.Path
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PathExtensionsTest {

    private var x = 1.0
    private var y = 2.0

    @MockK
    private lateinit var path: Path

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `When path line to extension is used then it call proper path line to method`() {
        path.lineTo(x, y)

        verify { path.lineTo(1F, 2F) }
    }

    @Test
    fun `When path move to extension is used then it call proper path move to method`() {
        path.moveTo(x, y)

        verify { path.moveTo(1F, 2F) }
    }
}