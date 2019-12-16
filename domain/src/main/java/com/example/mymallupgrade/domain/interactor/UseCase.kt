package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.common.Transformer
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

abstract class UseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String,Any>? = null): Observable<T>

    fun observable(withData: Map<String,Any>? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }
}