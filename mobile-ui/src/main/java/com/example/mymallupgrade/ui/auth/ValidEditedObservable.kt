package com.example.mymallupgrade.ui.auth

import android.widget.EditText
import com.example.mymallupgrade.domain.Result
import com.example.mymallupgrade.domain.UtilCheckValid
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

object ValidEditedObservable {
    fun execute(editText: TextInputEditText,editTextLayout : TextInputLayout,check:  (String) -> Result): Disposable =
        editText.textChanges()
            .skip(1)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { string ->
                Timber.d("textchange $string")
                when (val result = check(string.toString())) {
                    is Result.Failure -> editTextLayout.error = result.message
                    is Result.Success -> editTextLayout.error = null
                }
            }
}