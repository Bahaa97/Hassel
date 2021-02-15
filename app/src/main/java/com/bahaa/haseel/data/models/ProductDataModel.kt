package com.bahaa.haseel.data.models

import com.bahaa.haseel.R

data class ProductDataModel(
    var title: Int,
    var image: Int,
    var quantity: Int,
    var description: Int,
    var price: String
)

fun getProducts(): List<ProductDataModel> {
    val list: MutableList<ProductDataModel> = arrayListOf()
    list.add(ProductDataModel(R.string.productTitle1, R.drawable.product_1, 2, R.string.productDes1, "15.50"))
    list.add(ProductDataModel(R.string.productTitle2, R.drawable.product_2, 4, R.string.productDes2, "295.50"))
    list.add(ProductDataModel(R.string.productTitle3, R.drawable.product_3, 5,  R.string.productDes3, "132.50"))
    list.add(ProductDataModel(R.string.productTitle1, R.drawable.product_1, 2,  R.string.productDes1, "15.50"))
    list.add(ProductDataModel(R.string.productTitle2, R.drawable.product_2, 4,  R.string.productDes2, "295.50"))
    list.add(ProductDataModel(R.string.productTitle3, R.drawable.product_3, 5,  R.string.productDes3, "132.50"))
    list.add(ProductDataModel(R.string.productTitle1, R.drawable.product_1, 5,  R.string.productDes1, "295.50"))
    list.add(ProductDataModel(R.string.productTitle2, R.drawable.product_2, 5,  R.string.productDes2, "132.50"))
    return list
}