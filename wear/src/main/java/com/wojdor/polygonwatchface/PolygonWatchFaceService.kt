package com.wojdor.polygonwatchface

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.wearable.watchface.CanvasWatchFaceService
import android.support.wearable.watchface.WatchFaceService
import android.support.wearable.watchface.WatchFaceStyle
import android.view.SurfaceHolder
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.TimeUnit

private val INTERACTIVE_UPDATE_RATE_MS = TimeUnit.SECONDS.toMillis(1)

private const val MESSAGE_UPDATE_TIME = 3

private const val SECOND_TICK_STROKE_WIDTH = 2f

private const val CENTER_GAP_AND_CIRCLE_RADIUS = 4f

private const val SHADOW_RADIUS = 6f

class PolygonWatchFaceService : CanvasWatchFaceService() {

    override fun onCreateEngine() = Engine()

    private class EngineHandler(reference: PolygonWatchFaceService.Engine) : Handler() {
        private val mWeakReference: WeakReference<PolygonWatchFaceService.Engine> =
            WeakReference(reference)

        override fun handleMessage(message: Message) {
            mWeakReference.get()?.let {
                when (message.what) {
                    MESSAGE_UPDATE_TIME -> it.handleUpdateTimeMessage()
                }
            }
        }
    }

    inner class Engine : CanvasWatchFaceService.Engine() {

        private lateinit var mCalendar: Calendar

        private var mRegisteredTimeZoneReceiver = false
        private var mCenterX: Float = 0F
        private var mCenterY: Float = 0F

        private var mSecondHandLength: Float = 0F

        private var mWatchHandColor = Color.WHITE

        private lateinit var mPaint: Paint

        private val mBackgroundPaint = Paint().apply { color = Color.BLACK }

        private var mAmbient: Boolean = false
        private var mLowBitAmbient: Boolean = false
        private var mBurnInProtection: Boolean = false

        /* Handler to update the time once a second in interactive mode. */
        private val mUpdateTimeHandler = EngineHandler(this)

        private val mTimeZoneReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                mCalendar.timeZone = TimeZone.getDefault()
                invalidate()
            }
        }

        override fun onCreate(holder: SurfaceHolder) {
            super.onCreate(holder)
            setWatchFaceStyle(WatchFaceStyle.Builder(this@PolygonWatchFaceService).build())
            mCalendar = Calendar.getInstance()
            initializeWatchFace()
        }

        private fun initializeWatchFace() {
            mWatchHandColor = Color.WHITE
            mPaint = Paint().apply {
                color = mWatchHandColor
                strokeWidth = SECOND_TICK_STROKE_WIDTH
                isAntiAlias = true
                style = Paint.Style.STROKE
                setShadowLayer(SHADOW_RADIUS, 0f, 0f, mWatchHandColor)
            }
        }

        override fun onDestroy() {
            mUpdateTimeHandler.removeMessages(MESSAGE_UPDATE_TIME)
            super.onDestroy()
        }

        override fun onPropertiesChanged(properties: Bundle) {
            super.onPropertiesChanged(properties)
            mLowBitAmbient = properties.getBoolean(
                WatchFaceService.PROPERTY_LOW_BIT_AMBIENT, false
            )
            mBurnInProtection = properties.getBoolean(
                WatchFaceService.PROPERTY_BURN_IN_PROTECTION, false
            )
        }

        override fun onTimeTick() {
            super.onTimeTick()
            invalidate()
        }

        override fun onAmbientModeChanged(inAmbientMode: Boolean) {
            super.onAmbientModeChanged(inAmbientMode)
            mAmbient = inAmbientMode
            updateWatchHandStyle()
            updateTimer()
        }

        private fun updateWatchHandStyle() {
            if (mAmbient) {
                mPaint.color = Color.WHITE
                mPaint.isAntiAlias = false
                mPaint.clearShadowLayer()
            } else {
                mPaint.color = mWatchHandColor
                mPaint.isAntiAlias = true
                mPaint.setShadowLayer(SHADOW_RADIUS, 0f, 0f, mWatchHandColor)
            }
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
            mCenterX = width / 2f
            mCenterY = height / 2f
            mSecondHandLength = (mCenterX * 0.875).toFloat()
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            mCalendar.timeInMillis = System.currentTimeMillis()
            drawBackground(canvas)
            drawWatchFace(canvas)
        }

        private fun drawBackground(canvas: Canvas) {
            if (mAmbient && (mLowBitAmbient || mBurnInProtection)) {
                // Sleep (no details, black & white)
                canvas.drawColor(Color.BLACK)
            } else if (mAmbient) {
                // Ambient (with details, black $ white)
            } else {
                // Normal
                canvas.drawColor(Color.DKGRAY)
            }
        }

        private fun drawWatchFace(canvas: Canvas) {
            drawTicks(canvas)
            drawSecondsHand(canvas)
            drawCenterCircle(canvas)
        }

        private fun drawTicks(canvas: Canvas) {
            val innerTickRadius = mCenterX - 10
            val outerTickRadius = mCenterX
            for (tickIndex in 0..11) {
                val tickRot = (tickIndex.toDouble() * Math.PI * 2.0 / 12).toFloat()
                val innerX = Math.sin(tickRot.toDouble()).toFloat() * innerTickRadius
                val innerY = (-Math.cos(tickRot.toDouble())).toFloat() * innerTickRadius
                val outerX = Math.sin(tickRot.toDouble()).toFloat() * outerTickRadius
                val outerY = (-Math.cos(tickRot.toDouble())).toFloat() * outerTickRadius
                canvas.drawLine(
                    mCenterX + innerX, mCenterY + innerY,
                    mCenterX + outerX, mCenterY + outerY, mPaint
                )
            }
            canvas.save()
        }

        private fun drawSecondsHand(canvas: Canvas) {
            val secondsAndMinutesHandRotationPerTimeUnit = 360 / 60
            val minutesRotation =
                mCalendar.get(Calendar.MINUTE) * secondsAndMinutesHandRotationPerTimeUnit
            val seconds =
                mCalendar.get(Calendar.SECOND) + mCalendar.get(Calendar.MILLISECOND) / 1000f
            val secondsRotation = seconds * secondsAndMinutesHandRotationPerTimeUnit
            // Draw seconds when screen is on
            if (!mAmbient) {
                canvas.rotate(secondsRotation - minutesRotation, mCenterX, mCenterY)
                canvas.drawLine(
                    mCenterX,
                    mCenterY - CENTER_GAP_AND_CIRCLE_RADIUS,
                    mCenterX,
                    mCenterY - mSecondHandLength,
                    mPaint
                )
                canvas.restore()
            }
        }

        private fun drawCenterCircle(canvas: Canvas) {
            canvas.drawCircle(
                mCenterX,
                mCenterY,
                CENTER_GAP_AND_CIRCLE_RADIUS,
                mPaint
            )
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            if (visible) {
                registerReceiver()
                /* Update time zone in case it changed while we weren't visible. */
                mCalendar.timeZone = TimeZone.getDefault()
                invalidate()
            } else {
                unregisterReceiver()
            }
            updateTimer()
        }

        private fun registerReceiver() {
            if (mRegisteredTimeZoneReceiver) {
                return
            }
            mRegisteredTimeZoneReceiver = true
            val filter = IntentFilter(Intent.ACTION_TIMEZONE_CHANGED)
            this@PolygonWatchFaceService.registerReceiver(mTimeZoneReceiver, filter)
        }

        private fun unregisterReceiver() {
            if (!mRegisteredTimeZoneReceiver) {
                return
            }
            mRegisteredTimeZoneReceiver = false
            this@PolygonWatchFaceService.unregisterReceiver(mTimeZoneReceiver)
        }

        private fun updateTimer() {
            mUpdateTimeHandler.removeMessages(MESSAGE_UPDATE_TIME)
            if (shouldTimerBeRunning()) {
                mUpdateTimeHandler.sendEmptyMessage(MESSAGE_UPDATE_TIME)
            }
        }

        private fun shouldTimerBeRunning(): Boolean {
            return isVisible && !mAmbient
        }

        fun handleUpdateTimeMessage() {
            invalidate()
            if (shouldTimerBeRunning()) {
                val timeMs = System.currentTimeMillis()
                val delayMs = INTERACTIVE_UPDATE_RATE_MS - timeMs % INTERACTIVE_UPDATE_RATE_MS
                mUpdateTimeHandler.sendEmptyMessageDelayed(MESSAGE_UPDATE_TIME, delayMs)
            }
        }
    }
}