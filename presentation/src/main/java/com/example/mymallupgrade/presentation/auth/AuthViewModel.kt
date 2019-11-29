package com.example.mymallupgrade.presentation.auth

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.domain.interactor.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthViewModel(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModel() {
    //    private var user = User()
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var fullName: String = ""

    private val _loadingState = MutableLiveData<Int>()
    val loadingState : LiveData<Int>
        get() = _loadingState

    private val _errorState = MutableLiveData<String>()
    val errorState : LiveData<String>
        get() = _errorState

    private val _successState = MutableLiveData<Boolean>()
    val successState : LiveData<Boolean>
        get() = _successState

    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    fun signup() {
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
        _loadingState.value = View.VISIBLE

        val disposable = loginWithEmailUseCase(email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loadingState.value =  View.GONE
                _successState.value = true
            },{
                _loadingState.value =  View.GONE
                _errorState.value = it.message
            })

        disposables.add(disposable)
    }

    fun sendEmailResetPassword() {
        if(_successState.value == true) {
            _errorState.value = "You already send email reset password!"
            return
        }

        _loadingState.value = View.VISIBLE

        val disposable = sendEmailResetPasswordUseCase(email)
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

    override fun onCleared() {
        Timber.d("Cleared")
        super.onCleared()
    }
}