package ru.sample.store.binscaner

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bin(
    var number: String
) : Parcelable
