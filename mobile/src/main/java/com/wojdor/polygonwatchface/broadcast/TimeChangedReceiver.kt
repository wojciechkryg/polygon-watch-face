package com.wojdor.polygonwatchface.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class TimeChangedReceiver : BroadcastReceiver() {

    private val intentActions =
        listOf(Intent.ACTION_TIME_TICK, Intent.ACTION_TIME_CHANGED, Intent.ACTION_TIMEZONE_CHANGED)

    var onTimeChanged = {}

    val intentFilter = IntentFilter().apply {
        intentActions.forEach { addAction(it) }
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intentActions.contains(intent.action)) {
            onTimeChanged()
        }
    }
}