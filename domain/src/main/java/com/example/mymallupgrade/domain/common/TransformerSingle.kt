package com.example.mymallupgrade.domain.common

import io.reactivex.SingleTransformer

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
abstract class TransformerSingle<T> : SingleTransformer<T,T>