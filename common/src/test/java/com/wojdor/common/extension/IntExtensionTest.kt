package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class IntExtensionTest {

    @Test
    fun `When zero is formatted to minimum two digit string then it has zero before zero`() {
        assertEquals("00", 0.toMinTwoDigitString())
    }

    @Test
    fun `When number with one digit is formatted to minimum two digit string then it has zero before digit`() {
        assertEquals("01", 1.toMinTwoDigitString())
    }

    @Test
    fun `When number with two digit is formatted to minimum two digit string then it has two digit`() {
        assertEquals("39", 39.toMinTwoDigitString())
    }

    @Test
    fun `When number with three digit is formatted to minimum two digit string then it has three digit`() {
        assertEquals("100", 100.toMinTwoDigitString())
    }

    @Test
    fun `When negative number with one digit is formatted to minimum two digit string then it has zero before digit`() {
        assertEquals("-01", (-1).toMinTwoDigitString())
    }
}