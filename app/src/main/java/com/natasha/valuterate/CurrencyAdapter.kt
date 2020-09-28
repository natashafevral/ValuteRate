package com.natasha.valuterate

import android.app.Application
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.natasha.valuterate.viewmodel.CurrenciesViewModel
import com.natasha.valuterate.viewmodel.CurrenciesViewModelFactory
import kotlinx.android.synthetic.main.list_item.view.*


class CurrencyAdapter(var items: ArrayMap<String, Currency>): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    private var lastSelectedCurrency: Int = -1
    var currentCurrency: MutableLiveData<Currency> = MutableLiveData<Currency>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)

        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        var currency = getCurrencyByID(position)
        holder.bind(currency)
        holder.selectedCurrencyItem.isChecked = (position == lastSelectedCurrency)
    }
    override fun getItemCount() = items.size

    private fun getCurrencyByID(id: Int): Currency {
        val key = items.keyAt(id)
        return items[key]?: Currency()
    }

    inner class CurrencyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var selectedCurrencyItem: RadioButton

        fun bind(item: Currency) {

            itemView.char_code.text = item.charCode
            itemView.name.text = item.name
            itemView.nominal.text = item.nominal.toString()
            itemView.value.text = item.value.toString()
            var diff = item.value - item.previous
            var diffText = "%.1f".format(diff)
            var diffResult = "(${diffText})"
            if (diff >= 0) diffResult = "(+${diffText})"

            itemView.dif_value.text = diffResult

            selectedCurrencyItem = itemView.radioButton
            selectedCurrencyItem.setOnClickListener {
                notifyItemChanged(lastSelectedCurrency)
                lastSelectedCurrency = adapterPosition
                Log.d("currency holder click", lastSelectedCurrency.toString())
                notifyItemChanged(lastSelectedCurrency)
                currentCurrency.value = item

            }
        }
    }
}