package com.wojdor.polygonwatchface.configuration.choose

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.commonandroid.extension.inflate
import com.wojdor.polygonwatchface.R

class ChooseColorAdapter(private val items: List<Int>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ChooseColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChooseColorViewHolder(parent.inflate(R.layout.item_choose_color))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChooseColorViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }
}