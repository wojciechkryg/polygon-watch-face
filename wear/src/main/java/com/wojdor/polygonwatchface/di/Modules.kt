package com.wojdor.polygonwatchface.di

import com.wojdor.polygonwatchface.watchface.basic.BasicConfigurationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val repositoryModules = module {
    single { BasicConfigurationRepository(androidContext()) }
}

val appModules = listOf(repositoryModules)