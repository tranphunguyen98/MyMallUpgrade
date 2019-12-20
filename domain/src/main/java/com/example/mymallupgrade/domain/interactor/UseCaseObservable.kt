package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.common.TransformerObservable
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

abstract class UseCaseObservable<T>(private val transformerObservable: TransformerObservable<T>) {

    abstract fun createObservable(data: Map<String,Any>? = null): Observable<T>

    fun observable(withData: Map<String,Any>? = null): Observable<T> {
        return createObservable(withData).compose(transformerObservable)
    }
}