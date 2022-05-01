package com.osinit.internship.api

import com.osinit.internship.data.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {

    @GET("{date}/daily_json.js")
    suspend fun getCurrencies(@Path("date", encoded = true) date: String): CurrencyResponse
}

