package com.example.mymallupgrade.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val email: String = "",
    val name: String = "",
    val isAuthenticated: Boolean = false,
    val isNew: Boolean = false,
    val isCreated: Boolean = false
) : Parcelable