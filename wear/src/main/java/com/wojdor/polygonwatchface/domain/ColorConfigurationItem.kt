package com.wojdor.polygonwatchface.domain

class ColorConfigurationItem(
    textId: Int,
    val color: Int,
    val onClick: () -> Unit
) : BaseConfigurationItem(ConfigurationType.COLOR, textId)