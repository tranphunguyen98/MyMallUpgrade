package com.example.mymallupgrade.cache.mapper

import io.reactivex.Observable

abstract class Mapper<C,D> {
    abstract fun from(from: C): D
    abstract fun to(from : D): C

    fun observable(from: C) :Observable<D> {
        return Observable.fromCallable { from(from) }
    }

    fun observable(from: List<C>): Observable<List<D>> {
        return Observable.fromCallable { from.map { from(it) } }
    }
}