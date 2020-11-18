package com.wojdor.polygonwatchface.di

import com.wojdor.commonandroid.watchface.BasicWatchFace
import com.wojdor.polygonwatchface.watchface.basic.BasicConfigurationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val repositoryModules = module {
    single { BasicConfigurationRepository(androidContext()) }
}

private val watchFaceModules = module {
    single { BasicWatchFace() }
}

val appModules = listOf(repositoryModules, watchFaceModules)