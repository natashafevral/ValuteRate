package com.natasha.valuterate

import android.app.Application
import android.util.ArrayMap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRepository(private var application: Application) {
    private var currencyList: ArrayMap<String, Currency> = ArrayMap()
    private var currencyLiveData: MutableLiveData<ArrayMap<String, Currency>> = MutableLiveData()

    fun getMutableLiveData(): MutableLiveData<ArrayMap<String, Currency>> {
        var service = RetrofitBuilder().getApiService()
        val call = service.getCurrentСurrency()
        Log.d("get Weather call", "${call.request()}")
        call.enqueue(object: Callback<CurrentRate> {
            override fun onResponse(call: Call<CurrentRate>, response: Response<CurrentRate>) {
                Log.d("Valuta answer service", "response ${response.code()}")
                if (response.code() == 200) {
                    var result = response.body()
                    if (result != null) {
                        currencyList = result.currency
                        currencyLiveData.value = currencyList
                       // showCurrencyList()
                    }
                    Log.d("Response body", "${result?: "null"}")
                }
                Log.d("Weather answer service", "response ${response.body()?.date?:"no date"}")
            }

            override fun onFailure(call: Call<CurrentRate>, t: Throwable) {
                Log.d("Weather answer service", "failure ${t.toString()}")
            }
        })
        return currencyLiveData
    }
}