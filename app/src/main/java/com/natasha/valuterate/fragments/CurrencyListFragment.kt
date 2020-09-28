package com.natasha.valuterate.fragments

import android.app.Application
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.natasha.valuterate.Currency
import com.natasha.valuterate.CurrencyAdapter
import com.natasha.valuterate.R
import com.natasha.valuterate.viewmodel.CurrenciesViewModel
import com.natasha.valuterate.viewmodel.CurrenciesViewModelFactory
import com.natasha.valuterate.viewmodel.CurrencyViewModel
import com.natasha.valuterate.viewmodel.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.currency_list_fragment.*
import kotlinx.android.synthetic.main.list_item.view.*

class CurrencyListFragment: Fragment() {
    private lateinit var currencyList: ArrayMap<String, Currency>
    private lateinit var adapter: CurrencyAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var activitySwipeRefresh: SwipeRefreshLayout? = null
    private val currencyViewModel by lazy {
        var activity = this.activity!!.viewModelStore
         ViewModelProvider(activity, CurrencyViewModelFactory()).get(CurrencyViewModel::class.java)
    }

    private val currenciesViewModel by lazy {
        val currencyFactory = CurrenciesViewModelFactory(Application())
        ViewModelProvider(this@CurrencyListFragment, currencyFactory).get(CurrenciesViewModel::class.java)}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activitySwipeRefresh = activity?.findViewById(R.id.swipeRefresh)
        Log.d("on activity in frag", "activitySwipeRefresh == null ${activitySwipeRefresh == null}")
    }


    fun getDailyCurrency() {
        activitySwipeRefresh?.isRefreshing = true
        currenciesViewModel.getAllCurrencies().observe(this, Observer<ArrayMap<String, Currency>> {
            Log.d("get currency observer", "${it.keyAt(0)}")
            currencyList = it
            activitySwipeRefresh?.isRefreshing = false
            currencyViewModel.setSelectedCurrency(currencyList["USD"]?:Currency())
            prepareCurrencyList(currencyList)
        })


    }


    private fun prepareCurrencyList(currList: ArrayMap<String, Currency>) {
        if (!currList.isNullOrEmpty()) {
            adapter = CurrencyAdapter(currList)
            //Log.d("cur from activity","${adapter.currentCurrency}")
            layoutManager = LinearLayoutManager(this.context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            currency_list.layoutManager = layoutManager

            currency_list.adapter = adapter

            adapter.notifyDataSetChanged()

            adapter.currentCurrency.observe(this, Observer<Currency> {
                currencyViewModel.setSelectedCurrency(it)
                //Log.d("Click currency fragment", "${it.name}")
                var x = currencyViewModel.getSelectedCurrency()
                Log.d("Click currency fragment", "${x.value}")
            })

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.currency_list_fragment, container, false)

        getDailyCurrency()
        return view
    }

}