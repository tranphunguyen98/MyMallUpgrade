package com.example.mymallupgrade.common

import android.app.Application
import com.example.mymallupgrade.BuildConfig
import com.example.mymallupgrade.data.repository.auth.AuthRepositoryImpl
import com.example.mymallupgrade.data.repository.auth.FirebaseSourceImpl
import com.example.mymallupgrade.di.DaggerMainComponent
import com.example.mymallupgrade.di.MainComponent
import com.example.mymallupgrade.di.auth.AuthViewModelFactory
import com.example.mymallupgrade.di.movie.detail.DetailSubComponent
import com.example.mymallupgrade.di.movie.detail.MovieDetailModule
import com.example.mymallupgrade.di.movie.popular.PopularMoviesModule
import com.example.mymallupgrade.di.movie.popular.PopularSubComponent
import com.example.mymallupgrade.domain.interactor.auth.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.auth.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.auth.SignUpWithEmailUseCase
import org.jetbrains.annotations.NotNull
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class App : Application(), KodeinAware {
    lateinit var mainComponent: MainComponent
    private var popularMovieComponent: PopularSubComponent? = null
    private var movieDetailComponent: DetailSubComponent? = null
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        // AUTH - FIREBASE
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
        initDependencies()
    }
    private fun initDependencies() {
        mainComponent = DaggerMainComponent.create()
    }

    fun createPopularComponent(): PopularSubComponent {
        popularMovieComponent = mainComponent.plus(PopularMoviesModule())
        return popularMovieComponent!!
    }

    fun releasePopularComponent() {
        popularMovieComponent = null
    }

    fun createDetailComponent(): DetailSubComponent {
        movieDetailComponent = mainComponent.plus(MovieDetailModule())
        return movieDetailComponent!!
    }

    fun releaseDetailComponent() {
        movieDetailComponent = null
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