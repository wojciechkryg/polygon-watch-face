package com.wojdor.polygonwatchface.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ColorConfigurationItemTest {

    @Test
    fun `When color configuration item is created then it has proper values`() {
        val textId = 1
        val color = 2
        val onClick = {}

        val item = ColorConfigurationItem(textId, color, onClick)

        assertEquals(ConfigurationType.COLOR, item.type)
        assertEquals(textId, item.textResId)
        assertEquals(color, item.color)
        assertEquals(onClick, item.onClick)
    }
}