package com.example.mymallupgrade.presentation.auth.auth

import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpAction.*
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpResult.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserSignUpProcessorHolder(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) {
    private val emailProcessor =
        ObservableTransformer<EmailAction, EmailResult> { actions ->
            actions
                .map { action -> EmailResult.Success(action.email) }
                .cast(EmailResult::class.java)
                .onErrorReturn(EmailResult::Failure)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(EmailResult.Loading)

        }

    private val passwordProcessor =
        ObservableTransformer<PasswordAction, PasswordResult> { actions ->
            actions
                .map { action -> PasswordResult.Success(action.password) }
                .cast(PasswordResult::class.java)
                .onErrorReturn(PasswordResult::Failure)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(PasswordResult.Loading)

        }

    private val signUpProcessor =
        ObservableTransformer<SignUpAction, SignUpResult> { actions ->
            actions.flatMap { action ->
                signUpWithEmailUseCase(action.email, action.password)
                    .map { SignUpResult.Success }
                    .cast(SignUpResult::class.java)
                    .onErrorReturn(SignUpResult::Failure)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(SignUpResult.Loading)

            }
        }

    internal var actionProcessor =
        ObservableTransformer<UserSignUpAction, UserSignUpResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(EmailAction::class.java).compose(emailProcessor),
                    shared.ofType(PasswordAction::class.java).compose(passwordProcessor)
                )
                    .mergeWith(shared.ofType(SignUpAction::class.java).compose(signUpProcessor))
                    .mergeWith(
                        //Error for not implemented actions
                        shared.filter { v ->
                            v !is EmailAction
                                    && v !is PasswordAction
                                    && v !is SignUpAction
                        }.flatMap { w ->
                            Observable.error<UserSignUpResult>(
                                IllegalArgumentException("Unknown Action type: $w")
                            )
                        }
                    )
            }

        }
}