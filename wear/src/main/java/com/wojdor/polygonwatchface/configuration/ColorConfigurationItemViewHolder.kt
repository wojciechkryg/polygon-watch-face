package com.wojdor.polygonwatchface.configuration

import android.view.View
import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.domain.ColorConfigurationItem
import kotlinx.android.synthetic.main.item_color_configuration.view.*

class ColorConfigurationItemViewHolder(itemView: View) :
    BaseViewHolder<ColorConfigurationItem>(itemView) {

    override fun bind(item: ColorConfigurationItem) {
        with(itemView) {
            setOnClickListener { item.onClick() }
            itemColorConfigurationColor.color = item.color
            itemColorConfigurationText.setText(item.textResId)
        }
    }
}