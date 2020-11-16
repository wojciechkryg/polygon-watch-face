package com.wojdor.polygonwatchface.configuration.choose

import android.view.View
import com.wojdor.polygonwatchface.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_choose_color.view.*

class ChooseColorViewHolder(itemView: View) : BaseViewHolder<Int>(itemView) {

    fun bind(item: Int, onClick: (Int) -> Unit) {
        itemView.setOnClickListener { onClick(item) }
        bind(item)
    }

    override fun bind(item: Int) {
        with(itemView) {
            itemChooseColorColor.color = item
        }
    }
}