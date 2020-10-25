package com.wojdor.common.extension

import java.text.DecimalFormat

fun Int.toMinTwoDigitString(): String = DecimalFormat("00").format(this)