package com.example.mymallupgrade.ui.auth

import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.data.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    //    private var user = User()
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    fun signup() {

        Timber.d("SignUpMEE $email $password")

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }

        authListener?.onStarted()

        val disposable = repository.register(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

}