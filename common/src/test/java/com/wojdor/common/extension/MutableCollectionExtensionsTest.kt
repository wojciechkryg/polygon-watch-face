package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class MutableCollectionExtensionsTest {

    @Test
    fun `When clear and remove all extension is used then collection is cleared and filled with new items`() {
        val collection = mutableListOf(1, 2, 3, 4, 5)

        collection.clearAndAddAll(listOf(6, 7, 8))

        assertEquals(3, collection.size)
        assertEquals(6, collection[0])
        assertEquals(7, collection[1])
        assertEquals(8, collection[2])
    }

    @Test
    fun `When clear and remove all extension is used on empty collection then collection is filled with new items`() {
        val collection = mutableListOf<Int>()

        collection.clearAndAddAll(listOf(6, 7, 8))

        assertEquals(3, collection.size)
        assertEquals(6, collection[0])
        assertEquals(7, collection[1])
        assertEquals(8, collection[2])
    }
}