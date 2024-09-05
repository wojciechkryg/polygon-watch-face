package com.wojdor.polygonwatchface.configuration

import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.databinding.ItemColorConfigurationBinding
import com.wojdor.polygonwatchface.domain.ColorConfigurationItem

class ColorConfigurationItemViewHolder(private val binding: ItemColorConfigurationBinding) :
    BaseViewHolder<ColorConfigurationItem>(binding.root) {

    override fun bind(item: ColorConfigurationItem) {
        with(binding) {
            root.setOnClickListener { item.onClick() }
            itemColorConfigurationColor.color = item.color
            itemColorConfigurationText.setText(item.textResId)
        }
    }
}
