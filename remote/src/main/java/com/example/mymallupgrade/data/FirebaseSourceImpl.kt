package com.example.mymallupgrade.data

import com.example.mymallupgrade.domain.repository.AuthFirebaseSource
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import timber.log.Timber

class FirebaseSourceImpl : AuthFirebaseSource{
    init {
        Timber.d("CCC FirebaseSourceImpl")
    }
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
            if(!emitter.isDisposed) {
                if(task.isSuccessful) {
                    emitter.onComplete()
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }
    }

    override fun register(email: String, password: String) = Completable.create {emitter ->
        Thread.sleep(10000)
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
            if(!emitter.isDisposed) {
                if(task.isSuccessful) {
                    Timber.d("CCC1 FirebaseSourceImpl")
                    emitter.onComplete()
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }
    }

    override fun sendEmailResetPassword(email: String) = Completable.create { emitter ->
        Timber.d("sendEmailResetPassword")
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{task ->
            if(!emitter.isDisposed) {
                if(task.isSuccessful) {
                    emitter.onComplete()

                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }
    }
}