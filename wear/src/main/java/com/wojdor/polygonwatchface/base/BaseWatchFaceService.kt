package com.wojdor.polygonwatchface.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.support.wearable.watchface.CanvasWatchFaceService
import android.support.wearable.watchface.WatchFaceService
import android.support.wearable.watchface.WatchFaceStyle
import android.text.format.DateFormat
import android.view.SurfaceHolder
import android.view.WindowInsets
import com.wojdor.polygonwatchface.base.BaseEngineHandler.Companion.MESSAGE_UPDATE_TIME
import java.util.*

abstract class BaseWatchFaceService : CanvasWatchFaceService() {

    abstract val interactiveUpdateIntervalInMilliseconds: Long

    protected var isRound = false
        private set
    protected var centerX = 0F
        private set
    protected var centerY = 0F
        private set
    protected var isAmbient = false
        private set
    protected val isAmbientWithProtection
        get() = isAmbient && (isLowBitAmbient || isBurnInProtection)

    private var isLowBitAmbient = false
    private var isBurnInProtection = false
    private val calendar = Calendar.getInstance()

    abstract fun onEngineInit()
    abstract fun drawBackground(canvas: Canvas)
    abstract fun drawWatchFace(canvas: Canvas)
    abstract fun onAmbientModeChanged()

    fun getDigitalHours() = if (DateFormat.is24HourFormat(this)) {
        calendar[Calendar.HOUR_OF_DAY]
    } else {
        val hour = calendar[Calendar.HOUR]
        if (hour == 0) 12 else hour
    }

    fun getAnalogHours() = calendar[Calendar.HOUR]

    fun getMinutes() = calendar[Calendar.MINUTE]

    fun getSeconds() = calendar[Calendar.SECOND]

    fun getMilliseconds() = calendar[Calendar.MILLISECOND]

    override fun onCreateEngine() = Engine()

    inner class Engine : CanvasWatchFaceService.Engine() {

        private val updateTimeHandler = BaseEngineHandler(this)

        private val timeZoneReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                calendar.timeZone = TimeZone.getDefault()
                invalidate()
            }
        }

        private var isTimeZoneReceiverRegistered = false

        override fun onCreate(holder: SurfaceHolder) {
            super.onCreate(holder)
            setWatchFaceStyle(WatchFaceStyle.Builder(this@BaseWatchFaceService).build())
        }

        override fun onApplyWindowInsets(insets: WindowInsets?) {
            super.onApplyWindowInsets(insets)
            insets?.let {
                isRound = it.isRound
            }
            onEngineInit()
        }

        override fun onDestroy() {
            updateTimeHandler.removeMessages(MESSAGE_UPDATE_TIME)
            super.onDestroy()
        }

        override fun onPropertiesChanged(properties: Bundle) {
            super.onPropertiesChanged(properties)
            isLowBitAmbient = properties.getBoolean(
                WatchFaceService.PROPERTY_LOW_BIT_AMBIENT, false
            )
            isBurnInProtection = properties.getBoolean(
                WatchFaceService.PROPERTY_BURN_IN_PROTECTION, false
            )
        }

        override fun onTimeTick() {
            super.onTimeTick()
            invalidate()
        }

        override fun onAmbientModeChanged(inAmbientMode: Boolean) {
            super.onAmbientModeChanged(inAmbientMode)
            isAmbient = inAmbientMode
            onAmbientModeChanged()
            updateTimer()
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
            centerX = width / 2f
            centerY = height / 2f
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            calendar.timeInMillis = System.currentTimeMillis()
            drawBackground(canvas)
            drawWatchFace(canvas)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            if (visible) {
                registerReceiver()
                calendar.timeZone = TimeZone.getDefault()
                invalidate()
            } else {
                unregisterReceiver()
            }
            updateTimer()
        }

        private fun registerReceiver() {
            if (isTimeZoneReceiverRegistered) return
            isTimeZoneReceiverRegistered = true
            val filter = IntentFilter(Intent.ACTION_TIMEZONE_CHANGED)
            this@BaseWatchFaceService.registerReceiver(timeZoneReceiver, filter)
        }

        private fun unregisterReceiver() {
            if (isTimeZoneReceiverRegistered) {
                isTimeZoneReceiverRegistered = false
                this@BaseWatchFaceService.unregisterReceiver(timeZoneReceiver)
            }
        }

        private fun updateTimer() {
            updateTimeHandler.removeMessages(MESSAGE_UPDATE_TIME)
            if (isInteractive()) {
                updateTimeHandler.sendEmptyMessage(MESSAGE_UPDATE_TIME)
            }
        }

        private fun isInteractive() = isVisible && !isAmbient

        internal fun updateTime() {
            invalidate()
            if (isInteractive()) {
                val time = System.currentTimeMillis()
                val delay =
                    interactiveUpdateIntervalInMilliseconds - time % interactiveUpdateIntervalInMilliseconds
                updateTimeHandler.sendEmptyMessageDelayed(MESSAGE_UPDATE_TIME, delay)
            }
        }
    }
}
