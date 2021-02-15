package com.bahaa.haseel.data.models

import com.bahaa.haseel.R

data class PaymentWayDataModel(
    var selected: Boolean = false,
    var image: Int,
    var imageSelected: Int,
    var type: String
)

fun getPaymentWay(): List<PaymentWayDataModel> {
    val list: MutableList<PaymentWayDataModel> = arrayListOf()
    list.add(
        PaymentWayDataModel(
            false,
            R.drawable.ic_apple_pay_un_selected,
            R.drawable.ic_apple_selected,
            "apple"
        )
    )
    list.add(
        PaymentWayDataModel(
            false,
            R.drawable.ic__stc_un_selected,
            R.drawable.ic__stc_selected,
            "stc"
        )
    )
    list.add(
        PaymentWayDataModel(
            false,
            R.drawable.ic_visa_un_selected,
            R.drawable.ic_visa_selected,
            "visa"
        )
    )
    list.add(
        PaymentWayDataModel(
            false,
            R.drawable.ic_wire_less_un_selected,
            R.drawable.ic_wire_less_selected,
            "wireless"
        )
    )
    return list
}