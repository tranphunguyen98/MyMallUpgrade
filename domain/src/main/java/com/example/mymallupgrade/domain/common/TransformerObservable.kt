package com.example.mymallupgrade.domain.common

import io.reactivex.ObservableTransformer

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

abstract class TransformerObservable<T>: ObservableTransformer<T,T>