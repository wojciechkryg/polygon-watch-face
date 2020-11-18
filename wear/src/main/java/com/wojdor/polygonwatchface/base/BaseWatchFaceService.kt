package com.wojdor.polygonwatchface.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.Rect
import android.os.Bundle
import android.support.wearable.watchface.CanvasWatchFaceService
import android.support.wearable.watchface.WatchFaceService
import android.support.wearable.watchface.WatchFaceStyle
import android.text.format.DateFormat
import android.view.SurfaceHolder
import android.view.WindowInsets
import com.wojdor.common.domain.WatchFaceTime
import com.wojdor.commonandroid.watchface.BaseWatchFace
import com.wojdor.polygonwatchface.base.BaseEngineHandler.Companion.MESSAGE_UPDATE_TIME
import java.util.*

abstract class BaseWatchFaceService : CanvasWatchFaceService() {

    abstract val watchFace: BaseWatchFace
    abstract val interactiveUpdateIntervalInMilliseconds: Long

    var isRound = false
        private set
    var width = 0
        private set
    var height = 0
        private set
    val center = PointF()
    var isAmbient = false
        private set
    val isAmbientWithProtection get() = isAmbient && (isLowBitAmbient || isBurnInProtection)

    private var isLowBitAmbient = false
    private var isBurnInProtection = false
    private val calendar = Calendar.getInstance()

    abstract fun onEngineInit()
    abstract fun onAmbientModeChanged()

    internal open fun onVisibilityChanged(isVisible: Boolean) {}
    internal open fun onSurfaceChanged() {}

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
            watchFace.init(width, height)
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
            this@BaseWatchFaceService.width = width
            this@BaseWatchFaceService.height = height
            with(center) {
                x = width / 2F
                y = height / 2F
            }
            onSurfaceChanged()
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            calendar.timeInMillis = System.currentTimeMillis()
            with(watchFace) {
                drawBackground(canvas)
                drawWatchFace(
                    canvas,
                    WatchFaceTime(calendar, DateFormat.is24HourFormat(this@BaseWatchFaceService))
                )
            }
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            this@BaseWatchFaceService.onVisibilityChanged(visible)
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
