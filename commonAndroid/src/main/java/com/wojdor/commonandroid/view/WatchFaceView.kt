package com.wojdor.commonandroid.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import com.wojdor.common.domain.WatchFaceTime
import com.wojdor.commonandroid.watchface.BasicWatchFace
import java.util.*

class WatchFaceView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    View(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val watchFace = BasicWatchFace()
    var timeColor
        get() = watchFace.timeColor
        set(value) {
            watchFace.timeColor = value
        }
    var dialColor
        get() = dialPaint.color
        set(value) {
            dialPaint.color = value
        }
    var isOutline
        get() = watchFace.isOutline
        set(value) {
            watchFace.isOutline = value
        }
    private val dialPaint = Paint().apply { isAntiAlias = true }
    private var time = WatchFaceTime(Calendar.getInstance(), DateFormat.is24HourFormat(context))

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        watchFace.init(width, height)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = minOf(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2F, height / 2F, width / 2F, dialPaint)
        watchFace.drawWatchFace(canvas, time)
    }

    fun refreshTime() {
        time = WatchFaceTime(Calendar.getInstance(), DateFormat.is24HourFormat(context))
        invalidate()
    }
}
