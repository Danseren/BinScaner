package ru.sample.store.binscaner.model.dto


import com.google.gson.annotations.SerializedName

data class BinDTO(
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("brand")
    val brand: String = "Unknown",
    @SerializedName("country")
    val country: Country,
    @SerializedName("number")
    val number: Number,
    @SerializedName("prepaid")
    val prepaid: Boolean = false,
    @SerializedName("scheme")
    val scheme: String = "Unknown",
    @SerializedName("type")
    val type: String = "Unknown"
)