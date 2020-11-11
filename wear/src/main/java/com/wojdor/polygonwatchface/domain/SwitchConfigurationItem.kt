package com.wojdor.polygonwatchface.domain

class SwitchConfigurationItem(
    textId: Int,
    val isChecked: Boolean,
    val onChecked: (Boolean) -> Unit
) : BaseConfigurationItem(ConfigurationType.SWITCH, textId)