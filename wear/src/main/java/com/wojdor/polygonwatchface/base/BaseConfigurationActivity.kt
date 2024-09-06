package com.wojdor.polygonwatchface.base

import android.app.Activity
import android.os.Bundle
import com.wojdor.polygonwatchface.configuration.ConfigurationItemsAdapter
import com.wojdor.polygonwatchface.databinding.ActivityConfigurationBaseBinding

abstract class BaseConfigurationActivity : Activity() {

    private lateinit var binding: ActivityConfigurationBaseBinding
    abstract val configurationRepository: BaseConfigurationRepository
    abstract val items: List<BaseConfigurationItem>
    lateinit var adapter: ConfigurationItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.baseConfigurationItems) {
            isEdgeItemsCenteringEnabled = true
            setHasFixedSize(true)
            adapter = ConfigurationItemsAdapter().apply {
                items = this@BaseConfigurationActivity.items
            }.also {
                this@BaseConfigurationActivity.adapter = it
            }
            requestFocus()
        }
    }
}
