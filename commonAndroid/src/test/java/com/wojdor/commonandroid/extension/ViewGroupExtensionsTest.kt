package com.wojdor.commonandroid.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ViewGroupExtensionsTest {

    @MockK
    private lateinit var inflater: LayoutInflater

    @MockK
    private lateinit var view: ViewGroup

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        mockkStatic(LayoutInflater::class)
        every { LayoutInflater.from(any()) } returns inflater
    }

    @Test
    fun `When inflate extension is used then proper it inflates view properly`() {
        val layoutId = 1

        view.inflate(layoutId)

        verify { inflater.inflate(layoutId, view, false) }
    }
}