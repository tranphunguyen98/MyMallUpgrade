package com.example.mymallupgrade.domain.common

import com.example.mymallupgrade.domain.entity.movie.Optional
import io.reactivex.Observable

abstract class Mapper<E,T> {
    abstract fun mapFrom(from: E): T
    abstract fun to(from: T): E

    fun mapOptional(from: Optional<E>): Optional<T> {
        from.value?.let{
            return Optional.of(mapFrom(it))
        } ?: return Optional.empty()
    }

    fun observable(from: E) :Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }
}