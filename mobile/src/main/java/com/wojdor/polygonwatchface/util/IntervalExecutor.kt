package com.wojdor.polygonwatchface.util

import android.os.Handler

class IntervalExecutor(private val delayInMilliseconds: Long, private val callback: () -> Unit) {

    private val handler = Handler()
    private val handlerCallback = object : Runnable {
        override fun run() {
            callback()
            handler.postDelayed(this, delayInMilliseconds)
        }
    }

    fun start() {
        handler.postDelayed(handlerCallback, delayInMilliseconds)
    }

    fun stop() {
        handler.removeCallbacks(handlerCallback)
    }
}