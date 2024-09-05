package com.wojdor.polygonwatchface.configuration

import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.databinding.ItemSwitchConfigurationBinding
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem

class SwitchConfigurationItemViewHolder(private val binding: ItemSwitchConfigurationBinding) :
    BaseViewHolder<SwitchConfigurationItem>(binding.root) {

    override fun bind(item: SwitchConfigurationItem) {
        with(binding) {
            root.setOnClickListener { itemSwitchConfigurationSwitch.toggle() }
            with(itemSwitchConfigurationSwitch) {
                isChecked = item.isChecked
                setOnCheckedChangeListener { _, isChecked ->
                    item.onChecked(isChecked)
                }
            }
            itemSwitchConfigurationText.setText(item.textResId)
        }
    }
}
