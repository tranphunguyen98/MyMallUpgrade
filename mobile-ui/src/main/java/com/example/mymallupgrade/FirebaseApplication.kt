package com.example.mymallupgrade

import android.app.Application
import com.example.mymallupgrade.data.repository.auth.FirebaseSourceImpl
import com.example.mymallupgrade.data.repository.auth.AuthRepositoryImpl
import com.example.mymallupgrade.domain.interactor.auth.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.auth.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.auth.SignUpWithEmailUseCase
import com.example.mymallupgrade.di.AuthViewModelFactory
import org.jetbrains.annotations.NotNull
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
        bind() from singleton {
            SignUpWithEmailUseCase(
                instance()
            )
        }
        bind() from singleton {
            LoginWithEmailUseCase(
                instance()
            )
        }
        bind() from singleton {
            SendEmailResetPasswordUseCase(
                instance()
            )
        }
        bind() from provider {
            AuthViewModelFactory(
                instance(),
                instance(),
                instance()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    inner class DebugTree : Timber.DebugTree() {
        override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
            return String.format(
                "HelloBaby:%s:%s",
                super.createStackElementTag(element),
                element.lineNumber
            )
        }
    }
}