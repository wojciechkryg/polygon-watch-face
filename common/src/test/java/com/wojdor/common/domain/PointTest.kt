package com.wojdor.common.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class PointTest {

    @Test
    fun `When point is created from empty constructor then it has default values`() {
        val point = Point()

        assertEquals(0F, point.x)
        assertEquals(0F, point.y)
    }

    @Test
    fun `When point is created then it has proper values`() {
        val point = Point(1F, 2F)

        assertEquals(1F, point.x)
        assertEquals(2F, point.y)
    }
}