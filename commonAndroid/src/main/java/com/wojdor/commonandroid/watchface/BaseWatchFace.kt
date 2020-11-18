package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import android.graphics.Paint
import com.wojdor.common.domain.WatchFaceTime

abstract class BaseWatchFace {

    var timePaint = Paint()
    var backgroundColor = 0

    abstract fun init(width: Int, height: Int)
    abstract fun drawBackground(canvas: Canvas)
    abstract fun drawWatchFace(canvas: Canvas, time: WatchFaceTime)
}