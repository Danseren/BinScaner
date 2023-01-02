package ru.sample.store.binscaner.model.dto


import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("city")
    val city: String = "Unknown",
    @SerializedName("name")
    val name: String = "Unknown",
    @SerializedName("phone")
    val phone: String = "Unknown",
    @SerializedName("url")
    val url: String = "Unknown"
)