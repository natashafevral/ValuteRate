package com.natasha.valuterate.viewmodel

import android.app.Application
import android.util.ArrayMap
import androidx.lifecycle.*
import com.natasha.valuterate.Currency
import com.natasha.valuterate.CurrencyRepository

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {
    private var currencyRepository: CurrencyRepository

    init {
        currencyRepository = CurrencyRepository(application)
    }

    fun getAllCurrencies(): LiveData<ArrayMap<String, Currency>> {
        return currencyRepository.getMutableLiveData()
    }

}