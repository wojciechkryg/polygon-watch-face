package com.wojdor.polygonwatchface.watchface.basic

import android.content.Intent
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseConfigurationActivity
import com.wojdor.polygonwatchface.configuration.choose.ChooseColorActivity
import com.wojdor.polygonwatchface.domain.ColorConfigurationItem
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem
import org.koin.android.ext.android.inject

class BasicConfigurationActivity : BaseConfigurationActivity() {

    override val configurationRepository: BasicConfigurationRepository by inject()
    override val items
        get() = listOf(
            ColorConfigurationItem(
                R.string.configuration_time_color,
                configurationRepository.timeColor
            ) { launchChooseColorActivity() },
            SwitchConfigurationItem(
                R.string.configuration_always_outline,
                configurationRepository.isAlwaysOutline
            ) { configurationRepository.isAlwaysOutline = it },
            SwitchConfigurationItem(
                R.string.configuration_grayscale_in_ambient,
                configurationRepository.isGrayscaleInAmbient
            ) { configurationRepository.isGrayscaleInAmbient = it }
        )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == CHOOSE_COLOR_TIME_CODE) {
            configurationRepository.timeColor =
                getColorFromIntent(data, configurationRepository.timeColor)
            adapter.items = items
        }
    }

    private fun launchChooseColorActivity() {
        startActivityForResult(
            Intent(this, ChooseColorActivity::class.java), CHOOSE_COLOR_TIME_CODE
        )
    }

    private fun getColorFromIntent(intent: Intent?, defaultColor: Int) =
        intent?.getIntExtra(ChooseColorActivity.COLOR_KEY, defaultColor) ?: defaultColor

    companion object {
        private const val CHOOSE_COLOR_TIME_CODE = 0
    }
}
