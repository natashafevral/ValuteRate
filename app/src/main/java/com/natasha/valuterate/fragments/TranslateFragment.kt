package com.natasha.valuterate.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.natasha.valuterate.Currency
import com.natasha.valuterate.R
import com.natasha.valuterate.viewmodel.CurrencyViewModel
import com.natasha.valuterate.viewmodel.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.currency_translate_fragment.*

class TranslateFragment: Fragment() {

    private var currentCurrency: Currency = Currency()
    private val currencyViewModel by lazy {
        var activity = this.activity!!.viewModelStore
        ViewModelProvider(activity, CurrencyViewModelFactory()).get(CurrencyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("on create fragm2", "create")
        currencyViewModel.getSelectedCurrency().observe(this, {
            currentCurrency = it
            fillCurrencyData(currentCurrency)
            Log.d("from translate fragment", "${it.charCode}")
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rub_value.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                fillCurrencyData(currentCurrency)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.currency_translate_fragment, container, false)
        //lifecycle.addObserver(currencyViewModel)
        Log.d("createView Fragment2", "${lifecycle.currentState}")



       /* <fragment
        tools:layout="@layout/currency_list_fragment"
        android:id="@+id/list_fragment"
        android:name="com.natasha.valuterate.fragments.CurrencyListFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="5"/>*/


        return view
    }

    private fun fillCurrencyData(currency: Currency) {
        // nRub/value = x/nominal
        foreign_title.text = currency.charCode
        var rubValue = rub_value.text.toString().toDouble()

        foreign_value.text = "%.2f".format((rubValue * currency.nominal) / currency.value)
    }



}