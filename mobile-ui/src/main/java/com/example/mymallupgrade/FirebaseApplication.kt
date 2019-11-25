package com.example.mymallupgrade

import android.app.Application
import com.example.mymallupgrade.data.FirebaseSourceImpl
import com.example.mymallupgrade.data.repositories.AuthRepositoryImpl
import com.example.mymallupgrade.domain.interactor.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import com.example.mymallupgrade.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class FirebaseApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSourceImpl() }
        bind() from singleton {
            AuthRepositoryImpl(
                instance()
            )
        }
        bind() from singleton { SignUpWithEmailUseCase(instance()) }
        bind() from singleton { LoginWithEmailUseCase(instance()) }
        bind() from singleton { SendEmailResetPasswordUseCase(instance()) }
        bind() from provider { AuthViewModelFactory(instance(),instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}