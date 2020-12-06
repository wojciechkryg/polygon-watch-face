package com.wojdor.polygonwatchface.configuration

import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.item_switch_configuration.view.*
import org.junit.Test

class SwitchConfigurationItemViewHolderTest {

    @Test
    fun `When holder is bound then it setup the view properly`() {
        val switch = mockk<Switch>(relaxed = true)
        val textView = mockk<TextView>(relaxed = true)
        val view = mockk<View>(relaxed = true) {
            every { itemSwitchConfigurationSwitch } returns switch
            every { itemSwitchConfigurationText } returns textView
        }
        val holder = SwitchConfigurationItemViewHolder(view)
        val textId = 1
        val item = SwitchConfigurationItem(textId, true) {}

        holder.bind(item)

        verify {
            view.setOnClickListener(any())
            switch.isChecked = item.isChecked
            switch.setOnCheckedChangeListener(any())
            textView.setText(textId)
        }
    }
}