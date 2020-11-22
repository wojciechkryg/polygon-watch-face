package com.wojdor.polygonwatchface.configuration.choose

import android.view.View
import com.wojdor.commonandroid.view.ColorView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.item_choose_color.view.*
import org.junit.Test

class ChooseColorViewHolderTest {

    @Test
    fun `When holder is bound then it setup the view properly`() {
        val colorView = mockk<ColorView>(relaxed = true)
        val view = mockk<View>(relaxed = true) { every { itemChooseColorColor } returns colorView }
        val holder = ChooseColorViewHolder(view)
        val item = 3

        holder.bind(item) {}

        verify {
            view.setOnClickListener(any())
            colorView.color = item
        }
    }
}