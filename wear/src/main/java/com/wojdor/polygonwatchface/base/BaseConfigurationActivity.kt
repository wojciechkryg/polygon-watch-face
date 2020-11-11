package com.wojdor.polygonwatchface.base

import android.app.Activity
import android.os.Bundle
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.configuration.ConfigurationItemsAdapter
import com.wojdor.polygonwatchface.domain.BaseConfigurationItem
import kotlinx.android.synthetic.main.activity_configuration_watch_face.*

abstract class BaseConfigurationActivity : Activity() {

    abstract val configurationRepository: BaseConfigurationRepository
    abstract val configurationItems: List<BaseConfigurationItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration_watch_face)
        with(configurationWatchFaceItems) {
            isEdgeItemsCenteringEnabled = true
            setHasFixedSize(true)
            adapter = ConfigurationItemsAdapter(configurationItems)
            requestFocus()
        }
    }
}