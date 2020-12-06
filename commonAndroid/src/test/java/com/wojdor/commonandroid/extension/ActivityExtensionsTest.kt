package com.wojdor.commonandroid.extension

import android.app.Activity
import com.google.android.material.snackbar.Snackbar
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class ActivityExtensionsTest {

    @MockK
    private lateinit var snackbar: Snackbar

    @MockK
    private lateinit var activity: Activity

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        mockkStatic(Snackbar::class)
        every { snackbar.show() } just Runs
        every { Snackbar.make(any(), any<Int>(), any()) } returns snackbar
    }

    @Test
    fun `When show snackbar extension is used then it creates and shows short duration snackbar from main content with proper text id`() {
        activity.showSnackbar(1)

        verify {
            Snackbar.make(activity.findViewById(android.R.id.content), 1, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }

}