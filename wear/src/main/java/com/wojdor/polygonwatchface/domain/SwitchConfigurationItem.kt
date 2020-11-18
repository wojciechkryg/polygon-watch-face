package com.wojdor.polygonwatchface.domain

import com.wojdor.polygonwatchface.base.BaseConfigurationItem

class SwitchConfigurationItem(
    textId: Int,
    val isChecked: Boolean,
    val onChecked: (Boolean) -> Unit
) : BaseConfigurationItem(ConfigurationType.SWITCH, textId)