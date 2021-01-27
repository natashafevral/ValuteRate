package com.natasha.valuterate

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.natasha.valuterate.databinding.ListItemBinding


class CurrencyAdapter(var items: ArrayMap<String, Currency>, val context: Context): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    private var lastSelectedCurrency: Int = -1
    var currentCurrency: MutableLiveData<Currency> = MutableLiveData<Currency>()
    var redColor = ContextCompat.getColor(context, R.color.redAccent)
    var greenColor = ContextCompat.getColor(context, R.color.greenAccent)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)

        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val currency = getCurrencyByID(position)
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
        private val binding = ListItemBinding.bind(itemView)
        fun bind(item: Currency) {
            binding.charCode.text = item.charCode
            binding.name.text = item.name
            binding.nominal.text = item.nominal.toString()
            binding.value.text = item.value.toString()
            val diff = item.value - item.previous
            val diffText = "%.1f".format(diff)
            var diffResult = "(${diffText})"
            if (diff >= 0) {
                diffResult = "(+${diffText})"
                binding.difValue.setTextColor(greenColor)
            } else {
                binding.difValue.setTextColor(redColor)
            }
            binding.difValue.text = diffResult

            selectedCurrencyItem = binding.radioButton
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