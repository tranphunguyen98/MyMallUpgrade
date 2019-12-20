package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.common.TransformerCompletable
import io.reactivex.Completable

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

abstract class UseCaseCompletable(private val transformerCompletable: TransformerCompletable) {

    abstract fun createCompletable(data: Map<String,Any>? = null): Completable

    fun completable(withData: Map<String,Any>? = null): Completable {
        return createCompletable(withData).compose(transformerCompletable)
    }
}