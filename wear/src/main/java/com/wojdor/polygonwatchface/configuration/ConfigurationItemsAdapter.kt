package com.wojdor.polygonwatchface.configuration

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.commonandroid.extension.inflate
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.domain.BaseConfigurationItem
import com.wojdor.polygonwatchface.domain.ColorConfigurationItem
import com.wojdor.polygonwatchface.domain.ConfigurationType
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem

class ConfigurationItemsAdapter :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    var items = emptyList<BaseConfigurationItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (ConfigurationType.values()[viewType]) {
            ConfigurationType.COLOR ->
                ColorConfigurationItemViewHolder(parent.inflate(R.layout.item_color_configuration))
            ConfigurationType.SWITCH ->
                SwitchConfigurationItemViewHolder(parent.inflate(R.layout.item_switch_configuration))
        }

    override fun getItemViewType(position: Int) = items[position].type.ordinal

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = items[position]
        when (holder) {
            is ColorConfigurationItemViewHolder -> holder.bind(item as ColorConfigurationItem)
            is SwitchConfigurationItemViewHolder -> holder.bind(item as SwitchConfigurationItem)
        }
    }
}