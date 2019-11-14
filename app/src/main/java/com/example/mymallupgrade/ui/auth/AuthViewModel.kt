package com.example.mymallupgrade.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.data.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    init {
        Timber.d("ViewModel1111")
    }
    //    private var user = User()
    var email: String? = "tranphu199@gmail.com"
    var password: String? = "123456"
    var confirmPassword: String? ="123456"
    var fullName: String? = "Tran Phu Nguyen"

    var authListener: AuthListener? = null

    private val _eventJumpToSignUp = MutableLiveData<Boolean>()
    val eventJumpToSignUp : LiveData<Boolean>
        get() = _eventJumpToSignUp

    private val _eventJumpToSignIn = MutableLiveData<Boolean>()
    val eventJumpToSignIn : LiveData<Boolean>
        get() = _eventJumpToSignIn

    private val _eventJumpToForgotPassword = MutableLiveData<Boolean>()
    val eventJumpToForgotPassword : LiveData<Boolean>
        get() = _eventJumpToForgotPassword

    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    fun signup() {

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

    fun login() {
        if(email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty() || fullName.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }

        authListener?.onStarted()

        val disposable = repository.login(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun sendEmailResetPassword() {
        Timber.d("sendEmailResetPassword $email")

        if(email.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            Timber.d("sendEmailResetPassword1 $email")
            return
        }

        authListener?.onStarted()

        val disposable = repository.sendEmailResetPassword(email!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun jumpToSignIn() {
        _eventJumpToSignIn.value = true
    }

    fun jumpToSignUp() {
        _eventJumpToSignUp.value = true
    }

    fun jumpToForgotPassword() {
        _eventJumpToForgotPassword.value = true
    }
}