package com.natasha.valuterate

import android.util.ArrayMap
import com.google.gson.annotations.SerializedName

data class CurrentRate(
    @SerializedName("Date")
    var date: String? = null,
    @SerializedName("PreviousDate")
    var previousDate: String? = null,
    @SerializedName("PreviousURL")
    var previousURL: String? = null,
    @SerializedName("Timestamp")
    var timestamp: String? = null,
    @SerializedName("Valute")
    var currency: ArrayMap<String, Currency> = ArrayMap<String, Currency>()

)