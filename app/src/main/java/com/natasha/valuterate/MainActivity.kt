package com.natasha.valuterate

import android.app.Application
import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.natasha.valuterate.fragments.CurrencyListFragment
import com.natasha.valuterate.viewmodel.CurrenciesViewModel
import com.natasha.valuterate.viewmodel.CurrenciesViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.currency_list_fragment.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity()/*, CurrencyListFragment.onSelectedItemListener */{

   // private var translateFragment: TranslateFragment = supportFragmentManager.findFragmentById(R.id.translate_fragment) as TranslateFragment
   // private var listFragment = supportFragmentManager.findFragmentById(R.id.list_fragment) as CurrencyListFragment
    private lateinit var currencyList: ArrayMap<String, Currency>
    private lateinit var adapter: CurrencyAdapter
    private lateinit var layoutManager: LinearLayoutManager
   /* private val currenciesViewModel by lazy {
        val currencyFactory = CurrenciesViewModelFactory(Application())
        ViewModelProvider(this@MainActivity, currencyFactory).get(CurrenciesViewModel::class.java)}
*/
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       var fragmentManager = supportFragmentManager
       var fragment = fragmentManager.findFragmentById(R.id.list_fragment) as CurrencyListFragment
       swipeRefresh.setOnRefreshListener {
           Log.d("swipe refresh", "start")
           fragment.getDailyCurrency()
           Log.d("swipe refresh", "end")
       }
       //





        //
    }

/* private fun getDailyCurrency() {
        swipeRefresh.isRefreshing = true
        currenciesViewModel.getAllCurrencies().observe(this, Observer<ArrayMap<String, Currency>> {
            Log.d("get currency observer", "${it.keyAt(0)}")
            currencyList = it
            swipeRefresh.isRefreshing = false
            prepareCurrencyList()

        })

    }*/
/*
    private fun prepareCurrencyList() {
        if (!currencyList.isNullOrEmpty()) {
            adapter = CurrencyAdapter(currencyList)
            //Log.d("cur from activity","${adapter.currentCurrency}")
            layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            list_fragment.currency_list.layoutManager = layoutManager
            //listFragment.currency_list.layoutManager = layoutManager
            list_fragment.currency_list.adapter = adapter

            adapter.notifyDataSetChanged()
        }
    }*/
/*
    override fun onItemClick(currency: Currency) {

        translateFragment.fillCurrencyData(currency)


    }
*/

}

/*<fragment
        tools:layout="@layout/currency_translate_fragment"
        android:id="@+id/translate_fragment"
        android:name="com.natasha.valuterate.fragments.TranslateFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

        android:layout_weight="5"
    android:orientation="vertical"

        */