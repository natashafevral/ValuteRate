package com.natasha.valuterate

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder{

    private var retrofit: Retrofit? = null
    fun getApiService(): CurrencyService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(CurrencyService::class.java)
    }



}