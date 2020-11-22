package com.wojdor.commonandroid.view

import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ColorViewTest {

    private lateinit var view: ColorView

    @Before
    fun setup() {
        view = ColorView(mockk(relaxed = true))
    }

    @Test
    fun `When color is set up in ColorView then it returns same color`() {
        view.color = 3

        assertEquals(3, view.color)
    }
}