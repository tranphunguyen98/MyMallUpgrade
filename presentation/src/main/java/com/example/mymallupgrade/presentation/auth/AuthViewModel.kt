package com.example.mymallupgrade.presentation.auth

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.domain.interactor.SignInWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModel() {

    //    private var user = User()
    var email: String? = "tranphu199@gmail.com"
    var password: String? = "123456"
    var confirmPassword: String? ="123456"
    var fullName: String? = ""

    private val _eventJumpToSignUp = MutableLiveData<Boolean>()
    val eventJumpToSignUp : LiveData<Boolean>
        get() = _eventJumpToSignUp

    private val _eventJumpToSignIn = MutableLiveData<Boolean>()
    val eventJumpToSignIn : LiveData<Boolean>
        get() = _eventJumpToSignIn

    private val _eventJumpToForgotPassword = MutableLiveData<Boolean>()
    val eventJumpToForgotPassword : LiveData<Boolean>
        get() = _eventJumpToForgotPassword

    private val _loadingState = MutableLiveData<Int>()
    val loadingState : LiveData<Int>
        get() = _loadingState

    private val _errorState = MutableLiveData<String>()
    val errorState : LiveData<String>
        get() = _errorState

    private val _successState = MutableLiveData<Boolean>()
    val successState : LiveData<Boolean>
        get() = _successState

    init {
        Timber.d("ViewModel1111")
        _loadingState.value = View.GONE
    }
    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    fun signup() {

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            _errorState.value = "Please input all values"
            return
        }

        Timber.d("fulname $fullName")

        _loadingState.value = View.VISIBLE

        val disposable: Disposable = signUpWithEmailUseCase(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loadingState.value = View.GONE
                _successState.value = true
            },{
                _errorState.value = it.message
                _loadingState.value = View.GONE
            })

        disposables.add(disposable)
    }

    fun login() {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            _errorState.value = "Please input all values"
            return
        }

        _loadingState.value = View.VISIBLE

        val disposable = signInWithEmailUseCase(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loadingState.value =  View.GONE
                _successState.value = true
            },{
                _loadingState.value =  View.GONE
            })

        disposables.add(disposable)
    }

    fun sendEmailResetPassword() {
        Timber.d("sendEmailResetPassword $email")

        if(email.isNullOrEmpty()) {
            _errorState.value = "Please input all values"
            Timber.d("sendEmailResetPassword1 $email")
            return
        }

        if(_successState.value == true) {
            _errorState.value = "You already send email reset password!"
            return
        }

        _loadingState.value = View.VISIBLE

        val disposable = sendEmailResetPasswordUseCase(email!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loadingState.value =  View.GONE
                _successState.value = true
            },{
                _errorState.value = it.message
                _loadingState.value =  View.GONE
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

    override fun onCleared() {
        Timber.d("Cleared")
        super.onCleared()
    }
}