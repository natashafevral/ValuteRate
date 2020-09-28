package com.natasha.valuterate

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyService {
@GET("daily_json.js")
    fun getCurrentСurrency(): Call<CurrentRate>
}