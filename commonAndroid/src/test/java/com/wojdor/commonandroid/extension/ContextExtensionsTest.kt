package com.wojdor.commonandroid.extension

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ContextExtensionsTest {

    @MockK
    private lateinit var context: Context

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `When get int list extension is used then it obtains proper resource`() {
        val resId = 1

        context.getIntList(resId)

        verify { context.resources.getIntArray(resId) }
    }
}