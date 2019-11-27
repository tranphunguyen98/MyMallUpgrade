package com.example.mymallupgrade.data

import com.example.mymallupgrade.domain.repository.AuthFirebaseSource
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
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

//    override fun register(email: String, password: String) = Completable.create {emitter ->
//        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
//            if(!emitter.isDisposed) {
//                if(task.isSuccessful) {
//                    Timber.d("CCC1 FirebaseSourceImpl")
//                    emitter.onComplete()
//                } else {
//                    emitter.onError(task.exception!!)
//                }
//            }
//        }
//    }
    private var saveSubject: PublishSubject<Boolean> = PublishSubject.create()
    override fun register(email: String, password: String) : Observable<Boolean> {
        saveSubject = PublishSubject.create()
//        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
//            if(task.isSuccessful) {
//                saveSubject.onNext(true)
//            } else {
//                saveSubject.onError(task.exception!!)
//            }
//        }
        //saveSubject.onNext(true)
        saveSubject.onError(Throwable("Lỗi nè!"))
        return saveSubject
    }
    override fun sendEmailResetPassword(email: String) = Completable.create { emitter ->
        Timber.d("sendEmailResetPassword")
        Thread.sleep(2000)
        emitter.onComplete()
   //     emitter.onError(Throwable("Hu ne"))
//        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{task ->
//            if(!emitter.isDisposed) {
//                if(task.isSuccessful) {
//                    emitter.onComplete()
//
//                } else {
//                    emitter.onError(task.exception!!)
//                }
//            }
//        }
    }
}