package com.example.mymallupgrade.common

import com.example.mymallupgrade.domain.common.TransformerCompletable
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

class ASyncTransformerCompletable : TransformerCompletable() {
    override fun apply(upstream: Completable): CompletableSource {
        return upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}