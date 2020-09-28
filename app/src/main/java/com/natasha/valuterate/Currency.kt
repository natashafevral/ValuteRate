package com.natasha.valuterate

import com.google.gson.annotations.SerializedName

data class Currency (
    @SerializedName("ID")
    var id: String? = null,
    @SerializedName("NumCode")
    var numCode: String? = null,
    @SerializedName("CharCode")
    var charCode: String? = null,
    @SerializedName("Nominal")
    var nominal: Int = 0,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Value")
    var value: Double = 0.0,
    @SerializedName("Previous")
    var previous: Double = 0.0
)