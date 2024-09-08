package com.example.adminpanelfoodontheway.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfoodontheway.databinding.DeliveryItemBinding

class DeliveryAdapter(private val CustomerNames:ArrayList<String>,private val PaymentStatus:ArrayList<String>,private val DeliveryStatus:ArrayList<String>) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = DeliveryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliveryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int {
       return CustomerNames.size
    }
    inner class DeliveryViewHolder (private val binding : DeliveryItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                customerName.text = CustomerNames[position]
                paymentStatus.text = PaymentStatus[position]
                deliveryStatus.text = DeliveryStatus[position]
                val colorMap = mapOf("Recieved" to Color.GREEN,"Not Recieved" to Color.RED,"Pending" to Color.GRAY)
                paymentStatus.setTextColor(colorMap[PaymentStatus[position]]?:Color.BLACK)
                val colorMap2 = mapOf("Delivered" to Color.GREEN,"Not Delivered" to Color.RED)
                deliveryStatus.backgroundTintList = ColorStateList.valueOf(colorMap2[DeliveryStatus[position]]?:Color.BLACK)
            }
        }

    }
}
