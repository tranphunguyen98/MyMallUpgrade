package com.example.mymallupgrade.cache.mapper

import io.reactivex.Observable

abstract class Mapper<C,D> {
    abstract fun mapFrom(from: C): D

    fun observable(from: C) :Observable<D> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<C>): Observable<List<D>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }
}