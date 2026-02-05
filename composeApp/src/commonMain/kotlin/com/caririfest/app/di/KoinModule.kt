package com.caririfest.app.di

import co.touchlab.kermit.Logger
import com.caririfest.app.data.EventService
import com.caririfest.app.data.EventServiceImpl
import com.caririfest.app.data.database.AppDatabase
import com.caririfest.app.data.database.getDatabaseBuilder
import com.caririfest.app.helper.OnboardingManager
import com.caririfest.app.navigation.RootViewModel
import com.caririfest.app.network.KtoAPIClient
import com.caririfest.app.repository.EventRepository
import com.caririfest.app.repository.EventRepositoryImpl
import com.caririfest.app.ui.home.feed.FeedViewModel
import com.caririfest.app.ui.home.feed.detail.EventDetailViewModel
import com.caririfest.app.ui.onboarding.OnBoardingViewModel
import com.caririfest.app.ui.splash.SplashViewModel
import com.caririfest.app.wrapper.AppSettings
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val targetModule: Module

val appModule = module {

    viewModel { FeedViewModel(get(),get()) }

    viewModel {
        EventDetailViewModel(
            repository = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        OnBoardingViewModel(get())
    }

    viewModel {
        RootViewModel(get(),get())
    }

    single { Logger.withTag("CaririFestAppLogger") }

    single { Settings() }
    single { AppSettings(get()) }

    single { OnboardingManager(get()) }


}

val dataModule = module {
    single { getDatabaseBuilder(get()) }
    factory { get<AppDatabase>().eventDao() }

    single<EventRepository> { EventRepositoryImpl(get(), get()) }
}

val networkModule = module {
    single<HttpClient> { KtoAPIClient.httpClient }
    single<EventService> { EventServiceImpl(get()) }
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(appModule + dataModule + networkModule + targetModule)
    }
}