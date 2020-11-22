package com.wojdor.polygonwatchface.configuration

import com.wojdor.polygonwatchface.domain.ColorConfigurationItem
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConfigurationItemsAdapterTest {

    private var configurationItems = listOf(
        mockk<ColorConfigurationItem>(),
        mockk<SwitchConfigurationItem>(),
        mockk<SwitchConfigurationItem>()
    )
    private lateinit var adapter: ConfigurationItemsAdapter

    @Before
    fun setup() {
        adapter = spyk(ConfigurationItemsAdapter()) {
            every { notifyDataSetChanged() } just Runs
        }.apply {
            items = configurationItems
        }
    }

    @Test
    fun `When item count is obtained then proper count is returned`() {
        assertEquals(configurationItems.size, adapter.itemCount)
    }

    @Test
    fun `When item is bound then it calls bind`() {
        val position = 1
        val holder = spyk(SwitchConfigurationItemViewHolder(mockk(relaxed = true)))
        every { holder.bind(any()) } just Runs

        adapter.onBindViewHolder(holder, position)

        verify { holder.bind(configurationItems[position] as SwitchConfigurationItem) }
    }
}