package com.example.mymallupgrade.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String,
    val gmail: String,
    val name: String,
    val isAuthenticated: Boolean,
    val isNew: Boolean,
    val isCreated: Boolean
) : Parcelable