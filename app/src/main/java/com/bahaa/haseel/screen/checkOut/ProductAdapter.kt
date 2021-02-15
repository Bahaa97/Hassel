package com.bahaa.haseel.screen.checkOut

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bahaa.haseel.data.models.PaymentWayDataModel
import com.bahaa.haseel.data.models.ProductDataModel
import com.bahaa.haseel.databinding.ItemPaymentBinding
import com.bahaa.haseel.databinding.ItemProductBinding

class ProductAdapter constructor(var list: List<ProductDataModel>, val actions : ProductAction) : RecyclerView.Adapter<ProductAdapter.MyHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemProductBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }



    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.branchesRowBinding.data = list[position]
        holder.itemView.setOnClickListener {
            actions.onProductClick(list[position])
        }

    }

    inner class MyHolder(val branchesRowBinding: ItemProductBinding) :
            RecyclerView.ViewHolder(branchesRowBinding.root)

    interface ProductAction{
        fun onProductClick(item:ProductDataModel)
    }

}