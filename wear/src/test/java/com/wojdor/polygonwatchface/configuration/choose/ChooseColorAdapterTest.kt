package com.wojdor.polygonwatchface.configuration.choose

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChooseColorAdapterTest {

    private var items = listOf(1, 2, 3, 4)
    private lateinit var adapter: ChooseColorAdapter

    @Before
    fun setup() {
        adapter = ChooseColorAdapter(items) {}
    }

    @Test
    fun `When item count is obtained then proper count is returned`() {
        assertEquals(items.size, adapter.itemCount)
    }

    @Test
    fun `When item is bound then it calls bind`() {
        val position = 1
        val holder = spyk(ChooseColorViewHolder(mockk(relaxed = true)))
        every { holder.bind(any(), any()) } just Runs

        adapter.onBindViewHolder(holder, position)

        verify { holder.bind(items[position], any()) }
    }
}