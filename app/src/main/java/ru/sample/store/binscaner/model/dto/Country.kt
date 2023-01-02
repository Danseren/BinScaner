package ru.sample.store.binscaner.model.dto


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("alpha2")
    val alpha2: String = "Unknown",
    @SerializedName("currency")
    val currency: String = "Unknown",
    @SerializedName("emoji")
    val emoji: String = "Unknown",
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0,
    @SerializedName("name")
    val name: String = "Unknown",
    @SerializedName("numeric")
    val numeric: String = "Unknown"
)