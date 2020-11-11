package com.wojdor.polygonwatchface.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseConfigurationItemViewHolder
import com.wojdor.polygonwatchface.domain.BaseConfigurationItem
import com.wojdor.polygonwatchface.domain.ConfigurationType
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem

class ConfigurationItemsAdapter(private val items: List<BaseConfigurationItem>) :
    RecyclerView.Adapter<BaseConfigurationItemViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (ConfigurationType.values()[viewType]) {
            ConfigurationType.SWITCH -> createSwitchConfigurationItem(parent)
        }

    override fun getItemViewType(position: Int) = items[position].type.ordinal

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseConfigurationItemViewHolder<*>, position: Int) {
        when (holder) {
            is SwitchConfigurationItemViewHolder -> holder.bind(items[position] as SwitchConfigurationItem)
        }
    }

    private fun createSwitchConfigurationItem(parent: ViewGroup) =
        SwitchConfigurationItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_switch_configuration, parent, false)
        )
}