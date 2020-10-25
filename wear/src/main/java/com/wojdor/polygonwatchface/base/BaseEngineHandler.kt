package com.wojdor.polygonwatchface.base

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

internal class BaseEngineHandler(reference: BaseWatchFaceService.Engine) : Handler() {

    private val engine = WeakReference(reference)

    override fun handleMessage(message: Message) {
        engine.get()?.let {
            when (message.what) {
                MESSAGE_UPDATE_TIME -> it.updateTime()
            }
        }
    }

    companion object {
        internal const val MESSAGE_UPDATE_TIME = 0
    }
}