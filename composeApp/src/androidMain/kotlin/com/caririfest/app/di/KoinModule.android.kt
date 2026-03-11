package com.caririfest.app.di

import com.caririfest.app.data.database.getDatabaseBuilder
import com.caririfest.app.push.PushService
import com.caririfest.app.pushAndroid.AndroidPushService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


actual val targetModule: Module = module {

    single { getDatabaseBuilder(androidContext()) }

    single<PushService> { AndroidPushService() }
}