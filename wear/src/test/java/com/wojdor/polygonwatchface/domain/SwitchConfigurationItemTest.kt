package com.wojdor.polygonwatchface.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class SwitchConfigurationItemTest {

    @Test
    fun `When switch configuration item is created then it has proper values`() {
        val textId = 1
        val isChecked = true
        val onChecked: (Boolean) -> Unit = {}

        val item = SwitchConfigurationItem(textId, isChecked, onChecked)

        assertEquals(ConfigurationType.SWITCH, item.type)
        assertEquals(textId, item.textResId)
        assertEquals(isChecked, item.isChecked)
        assertEquals(onChecked, item.onChecked)
    }
}