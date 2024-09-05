package com.wojdor.polygonwatchface.configuration.choose

import com.wojdor.polygonwatchface.base.BaseViewHolder
import com.wojdor.polygonwatchface.databinding.ItemChooseColorBinding

class ChooseColorViewHolder(private val binding: ItemChooseColorBinding) :
    BaseViewHolder<Int>(binding.root) {

    fun bind(item: Int, onClick: (Int) -> Unit) {
        binding.root.setOnClickListener { onClick(item) }
        bind(item)
    }

    override fun bind(item: Int) {
        binding.itemChooseColorColor.color = item
    }
}
