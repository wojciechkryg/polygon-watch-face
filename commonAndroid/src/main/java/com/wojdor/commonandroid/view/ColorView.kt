package com.wojdor.commonandroid.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ColorView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    View(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val paint = Paint().apply { isAntiAlias = true }
    var color
        get() = paint.color
        set(value) {
            paint.color = value
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width.toFloat() / 2, height.toFloat() / 2, width.toFloat() / 2, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = minOf(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(size, size)
    }
}
