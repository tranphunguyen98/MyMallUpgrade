package com.example.mymallupgrade.presentation.auth.MVIBase

import android.database.Observable

interface MviViewModel<I : MviIntent, S : MviViewState>  {
    fun processIntents(intents: Observable<I>)
    fun states(): Observable<S>
}