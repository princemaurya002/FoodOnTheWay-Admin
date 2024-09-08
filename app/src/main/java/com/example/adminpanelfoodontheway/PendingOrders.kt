package com.example.adminpanelfoodontheway

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodontheway.adapter.PendingAdapter
import com.example.adminpanelfoodontheway.databinding.ActivityPendingOrdersBinding

class PendingOrders : AppCompatActivity() {
    private val binding : ActivityPendingOrdersBinding by lazy {
        ActivityPendingOrdersBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val CustomerNames = listOf("Ram","Mohan","Shyam","Sunder")
        val itemQuantities = listOf("3","5","1","8")
        val itemImages = listOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3,R.drawable.menu4)


        val adapter = PendingAdapter(ArrayList(CustomerNames),ArrayList(itemQuantities),ArrayList(itemImages))
        binding.PendingRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.PendingRecyclerView.adapter =adapter

        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}