package com.bahaa.haseel.screen.checkOut

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bahaa.haseel.data.models.CardDataModel
import com.bahaa.haseel.data.models.PaymentWayDataModel
import com.bahaa.haseel.databinding.ItemCardBinding
import com.bahaa.haseel.databinding.ItemPaymentBinding

class CardsAdapter constructor(var list: List<CardDataModel>, val actions : CardsAction) : RecyclerView.Adapter<CardsAdapter.MyHolder>() {

    lateinit var context: Context
    var lastItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemCardBinding.inflate(
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
            actions.onCardClick(list[position])
        }

    }

    inner class MyHolder(val branchesRowBinding: ItemCardBinding) :
            RecyclerView.ViewHolder(branchesRowBinding.root)

    interface CardsAction{
        fun onCardClick(item:CardDataModel)
    }

}