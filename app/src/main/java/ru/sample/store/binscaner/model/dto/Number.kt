package ru.sample.store.binscaner.model.dto


import com.google.gson.annotations.SerializedName

data class Number(
    @SerializedName("length")
    val length: Int = 0,
    @SerializedName("luhn")
    val luhn: Boolean = false
)