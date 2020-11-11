package com.wojdor.polygonwatchface.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.polygonwatchface.domain.BaseConfigurationItem

abstract class BaseConfigurationItemViewHolder<T : BaseConfigurationItem>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}