package com.wojdor.polygonwatchface.configuration

import android.view.View
import com.wojdor.polygonwatchface.base.BaseConfigurationItemViewHolder
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem
import kotlinx.android.synthetic.main.item_switch_configuration.view.*

class SwitchConfigurationItemViewHolder(itemView: View) :
    BaseConfigurationItemViewHolder<SwitchConfigurationItem>(itemView) {

    override fun bind(item: SwitchConfigurationItem) {
        with(itemView) {
            setOnClickListener { itemSwitchConfigurationSwitch.toggle() }
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