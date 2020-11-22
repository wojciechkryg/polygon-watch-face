package com.wojdor.polygonwatchface.configuration

import android.view.View
import android.widget.TextView
import com.wojdor.commonandroid.view.ColorView
import com.wojdor.polygonwatchface.domain.ColorConfigurationItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.item_color_configuration.view.*
import org.junit.Test

class ColorConfigurationItemViewHolderTest {

    @Test
    fun `When holder is bound then it setup the view properly`() {
        val colorView = mockk<ColorView>(relaxed = true)
        val textView = mockk<TextView>(relaxed = true)
        val view = mockk<View>(relaxed = true) {
            every { itemColorConfigurationColor } returns colorView
            every { itemColorConfigurationText } returns textView
        }
        val holder = ColorConfigurationItemViewHolder(view)
        val textId = 1
        val color = 3
        val item = ColorConfigurationItem(textId, color) {}

        holder.bind(item)

        verify {
            view.setOnClickListener(any())
            colorView.color = item.color
            textView.setText(textId)
        }
    }
}