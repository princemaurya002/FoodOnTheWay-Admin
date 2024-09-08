package com.example.adminpanelfoodontheway.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfoodontheway.databinding.PendingItemBinding

class PendingAdapter(
    private val customerNames: ArrayList<String>,
    private val quantities: ArrayList<String>,
    private val foodImages: ArrayList<Int>,
) :
    RecyclerView.Adapter<PendingAdapter.PendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingViewHolder {
        val binding = PendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PendingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return customerNames.size
    }

    inner class PendingViewHolder(private val binding: PendingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var isAccepted = false
            binding.apply {
                customerName.text = customerNames[position]
                quantity.text = quantities[position]
                itemImage.setImageResource(foodImages[position])
                orderAcceptButton.apply {

                    if (!isAccepted) {
                        text = "Accept"
                    } else {
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!isAccepted) {
                            text = "Dispatch"
                            isAccepted = true
                            Toast.makeText(context, "Order is Accepted", Toast.LENGTH_SHORT).show()
                        } else if (isAccepted) {
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            Toast.makeText(context, "Order is Dispatched", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

        }
    }

}

