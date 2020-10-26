package com.wojdor.polygonwatchface.base

abstract class BaseDigitalWatchFaceService : BaseWatchFaceService() {

    fun getFirstDigitOfHours() = getDigitalHours() / 10

    fun getSecondDigitOfHours() = getDigitalHours() % 10

    fun getFirstDigitOfMinutes() = getMinutes() / 10

    fun getSecondDigitOfMinutes() = getMinutes() % 10

    fun getFirstDigitOfSeconds() = getSeconds() / 10

    fun getSecondDigitOfSeconds() = getSeconds() % 10
}