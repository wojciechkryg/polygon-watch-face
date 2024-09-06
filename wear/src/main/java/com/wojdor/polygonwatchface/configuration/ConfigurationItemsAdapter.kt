package com.wojdor.polygonwatchface.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.polygonwatchface.base.BaseConfigurationItem
import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.databinding.ItemColorConfigurationBinding
import com.wojdor.polygonwatchface.databinding.ItemSwitchConfigurationBinding
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
        when (ConfigurationType.entries[viewType]) {
            ConfigurationType.COLOR ->
                ColorConfigurationItemViewHolder(
                    ItemColorConfigurationBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )

            ConfigurationType.SWITCH ->
                SwitchConfigurationItemViewHolder(
                    ItemSwitchConfigurationBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
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
