package com.example.mymallupgrade.data

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import timber.log.Timber

class FirebaseSource {
    init {
        Timber.d("CCC FirebaseSource")
    }
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(email: String, password: String) = Completable.create {emitter ->
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

    fun register(email: String, password: String) = Completable.create {emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
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