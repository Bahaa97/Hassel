package com.bahaa.haseel.screen.checkOut

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bahaa.haseel.data.models.PaymentWayDataModel
import com.bahaa.haseel.databinding.ItemPaymentBinding

class PaymentAdapter constructor(var list: List<PaymentWayDataModel>, val actions : PaymentAction) : RecyclerView.Adapter<PaymentAdapter.MyHolder>() {

    lateinit var context: Context
    var lastItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemPaymentBinding.inflate(
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
            if (list[position].selected){
                return@setOnClickListener
            }
            if (lastItemPosition != -1){
                list[lastItemPosition].selected = false
                notifyItemChanged(lastItemPosition,null)
            }
            list[position].selected = true
            lastItemPosition = position
            notifyItemChanged(position,null)
            actions.onPaymentClick(list[position])
        }

    }

    inner class MyHolder(val branchesRowBinding: ItemPaymentBinding) :
            RecyclerView.ViewHolder(branchesRowBinding.root)

    interface PaymentAction{
        fun onPaymentClick(item:PaymentWayDataModel)
    }

}