package com.example.mymallupgrade.ui.auth

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
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
    fun execute(editText: TextInputEditText, editTextLayout : TextInputLayout): Observable<String>  {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                editTextLayout.error= null
            }
        })

        return  editText.textChanges()
            .skip(1)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}