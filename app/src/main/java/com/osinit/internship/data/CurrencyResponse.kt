package com.osinit.internship.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousUrl: String,
    @SerializedName("Timestamp")
    val temestamp: String,
    @SerializedName("Valute")
    val currencies: Map<String, CurrencyInfo>
)
