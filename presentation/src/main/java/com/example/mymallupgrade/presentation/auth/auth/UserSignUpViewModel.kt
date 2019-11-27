package com.example.mymallupgrade.presentation.auth.auth

import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.presentation.auth.MVIBase.MviViewModel
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpAction.*
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpIntent.*
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpResult.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class UserSignUpViewModes(
    private val actionProcessorHolder: UserSignUpProcessorHolder
) : ViewModel(),
    MviViewModel<UserSignUpIntent, UserSignUpViewState> {

    private val intentSubject: PublishSubject<UserSignUpIntent> = PublishSubject.create()
    private val stateObservable: Observable<UserSignUpViewState> = compose()

    override fun processIntents(intents: Observable<UserSignUpIntent>) {
        intents.subscribe(intentSubject)
    }

    override fun states(): Observable<UserSignUpViewState> = stateObservable

    private fun compose() : Observable<UserSignUpViewState>{
        Timber.d("compose")
        return intentSubject
            .map(this::actionFromIntent)
            .compose(actionProcessorHolder.actionProcessor)
            .scan(UserSignUpViewState.default(), reducer)
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    private fun actionFromIntent(intent: UserSignUpIntent) : UserSignUpAction = when(intent) {
        is EmailIntent -> EmailAction(intent.email)
        is PasswordIntent -> PasswordAction(intent.password)
        is SignUpIntent -> SignUpAction(intent.email,intent.password)
    }

    companion object {
        private val reducer = BiFunction {previousState: UserSignUpViewState,result: UserSignUpResult ->
            when(result) {
                is EmailResult -> {
                    Timber.d("reducer EmailResult")
                    reduceEmail(previousState,result)
                }
                is PasswordResult -> {
                    Timber.d("reducer PasswordResult")
                    reducePassword(previousState,result)
                }
                is SignUpResult -> {
                    Timber.d("reducer SignUpResult")
                    reduceSignUp(previousState,result)
                }
            }
        }
        private fun reduceEmail(
            previousState: UserSignUpViewState,
            result: EmailResult
        ): UserSignUpViewState = when(result) {
            is EmailResult.Success -> {
                Timber.d("reduceEmail Success")
                previousState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = null)
            }
            is EmailResult.Failure -> {
                Timber.d("reduceEmail Failure")
                previousState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = result.error
                )
            }
            is EmailResult.Loading -> {
                Timber.d("reduceEmail Loading")
                previousState.copy(
                    isLoading = true,
                    isSuccess = false,
                    isError = null
                )
            }
        }

        private fun reducePassword(
            previousState: UserSignUpViewState,
            result: PasswordResult) : UserSignUpViewState = when(result) {
            is PasswordResult.Success -> {
                Timber.d("reducePassword Success")
                previousState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = null)
            }

            is PasswordResult.Failure -> {
                Timber.d("reducePassword Failure")
                previousState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = result.error)
            }

            is PasswordResult.Loading -> {
                Timber.d("reducePassword Loading")
                previousState.copy(
                    isLoading = true,
                    isSuccess = false,
                    isError = null)
            }
        }

        private fun reduceSignUp(
            previousState: UserSignUpViewState,
            result: SignUpResult) : UserSignUpViewState = when(result) {
            is SignUpResult.Success -> {
                Timber.d("reduceSignUp Loading")
                previousState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = null)
            }

            is SignUpResult.Failure -> {
                Timber.d("reduceSignUp Failure")
                previousState.copy(
                    isLoading = false,
                    isSuccess = false,
                    isError = result.error)
            }

            is SignUpResult.Loading -> {
                Timber.d("reduceSignUp Loading")
                previousState.copy(
                    isLoading = true,
                    isSuccess = false,
                    isError = null)
            }
        }
    }


}