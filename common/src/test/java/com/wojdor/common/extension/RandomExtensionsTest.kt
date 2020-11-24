package com.wojdor.common.extension

import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class RandomExtensionsTest {

    @Test
    fun `When next list item extension is used then item from list is returned`() {
        val list = listOf(1, 2, 3, 4, 5)

        val item = Random.Default.nextListItem(list)

        assertTrue(list.contains(item))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `When next list item extension is used on empty list then IllegalArgumentException is thrown`() {
        Random.Default.nextListItem(emptyList<Int>())
    }
}