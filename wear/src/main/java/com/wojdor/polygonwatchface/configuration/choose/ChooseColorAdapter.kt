package com.wojdor.polygonwatchface.configuration.choose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.polygonwatchface.databinding.ItemChooseColorBinding

class ChooseColorAdapter(private val items: List<Int>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ChooseColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChooseColorViewHolder(
            ItemChooseColorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChooseColorViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }
}
