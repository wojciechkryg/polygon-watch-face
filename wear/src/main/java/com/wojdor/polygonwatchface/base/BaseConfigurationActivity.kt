package com.wojdor.polygonwatchface.base

import android.app.Activity
import android.os.Bundle
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.configuration.ConfigurationItemsAdapter
import com.wojdor.polygonwatchface.domain.BaseConfigurationItem
import kotlinx.android.synthetic.main.activity_configuration_base.*

abstract class BaseConfigurationActivity : Activity() {

    abstract val configurationRepository: BaseConfigurationRepository
    abstract val items: List<BaseConfigurationItem>
    lateinit var adapter: ConfigurationItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration_base)
        with(baseConfigurationItems) {
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