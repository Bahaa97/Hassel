package com.bahaa.haseel.data.models

import com.bahaa.haseel.R

data class CardDataModel(
    var selected: Boolean = false,
    var image: Int,
    var type: String
)

fun getCards(): List<CardDataModel> {
    val list: MutableList<CardDataModel> = arrayListOf()
    list.add(
        CardDataModel(
            false,
            R.drawable.ic_visa_selected,
            "visa"
        )
    )
    list.add(
        CardDataModel(
            false,
            R.drawable.ic__mada_selected,
            "mada"
        )
    )
    return list
}