package com.caririfest.app.di

import com.caririfest.app.data.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


actual val targetModule: Module = module {

    single { getDatabaseBuilder(androidContext()) }
}