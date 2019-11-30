package com.example.mymallupgrade.data.repository.auth

import com.example.mymallupgrade.domain.repository.auth.AuthDataSource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import timber.log.Timber

class FirebaseSourceImpl : AuthDataSource {
    init {
        Timber.d("CCC FirebaseSourceImpl")
    }

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private fun <T> createCompletableByTask(taskResult: Task<T>): Completable {
        return Completable.create { emitter ->
            taskResult.addOnCompleteListener { task ->
                if (!emitter.isDisposed) {
                    if (task.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }
            }
        }
    }

    override fun login(email: String, password: String) = createCompletableByTask<AuthResult>(
        firebaseAuth.signInWithEmailAndPassword(email, password)
    )

    override fun register(email: String, password: String) = createCompletableByTask<AuthResult>(
        firebaseAuth.createUserWithEmailAndPassword(email, password)
    )

    override fun sendEmailResetPassword(email: String) = createCompletableByTask<Void>(
        firebaseAuth.sendPasswordResetEmail(email)
    )
}