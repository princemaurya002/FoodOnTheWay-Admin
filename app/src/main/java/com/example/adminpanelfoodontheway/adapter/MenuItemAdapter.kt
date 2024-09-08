package com.example.adminpanelfoodontheway.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanelfoodontheway.databinding.AllItemsItemBinding
import com.example.adminpanelfoodontheway.model.AddMenu
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AddMenu>,
    databaseReference: DatabaseReference
): RecyclerView.Adapter<MenuItemAdapter.CartViewHolder>() {


    private val itemQuantity = IntArray(menuList.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = AllItemsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    inner class CartViewHolder (private val binding:AllItemsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantity[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)

                itemName.text = menuItem.foodName
                itemPrice.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(itemImage)
                cartItemQuantity.text = quantity.toString()

                plusButton.setOnClickListener(){
                    increaseQuantity(position)
                }
                minusButton.setOnClickListener(){
                    decreaseQuantity(position)
                }
                deleteButton.setOnClickListener(){
                    val itemPosition = adapterPosition
                    if(itemPosition!= RecyclerView.NO_POSITION) {
                        deleteQuantity(position)
                    }
                }

            }
        }
        private fun decreaseQuantity(position: Int){
            if(itemQuantity[position]>1){
                itemQuantity[position]--
                binding.cartItemQuantity.text=itemQuantity[position].toString()
            }
        }
        private fun increaseQuantity(position: Int){
            if(itemQuantity[position]<10){
                itemQuantity[position]++
                binding.cartItemQuantity.text=itemQuantity[position].toString()
            }
        }
        private fun deleteQuantity(position: Int){
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,menuList.size)
        }

    }
}