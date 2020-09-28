package com.natasha.valuterate.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natasha.valuterate.Currency

class CurrencyViewModel: ViewModel(), LifecycleObserver {
    private val selectedCurrency: MutableLiveData<Currency> = MutableLiveData<Currency>()

    fun setSelectedCurrency(currency: Currency) {
         selectedCurrency.value = currency
         //selectedCurrency.postValue(currency)
     }

     fun getSelectedCurrency(): LiveData<Currency> {
         Log.d("view model", "getter ${selectedCurrency.value}")
         return selectedCurrency
     }


}