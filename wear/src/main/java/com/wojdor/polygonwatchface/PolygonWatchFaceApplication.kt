package com.wojdor.polygonwatchface

import android.app.Application
import com.wojdor.polygonwatchface.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PolygonWatchFaceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PolygonWatchFaceApplication)
            modules(appModules)
        }
    }
}