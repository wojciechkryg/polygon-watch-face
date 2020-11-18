package com.wojdor.polygonwatchface.domain

import com.wojdor.polygonwatchface.base.BaseConfigurationItem

class ColorConfigurationItem(
    textId: Int,
    val color: Int,
    val onClick: () -> Unit
) : BaseConfigurationItem(ConfigurationType.COLOR, textId)