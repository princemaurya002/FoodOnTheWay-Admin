package com.example.adminpanelfoodontheway

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodontheway.adapter.DeliveryAdapter
import com.example.adminpanelfoodontheway.databinding.ActivityAllMenuBinding
import com.example.adminpanelfoodontheway.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding : ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val customerName = arrayListOf("Ram","Mohan","Shyam")
        val paymentStatus = arrayListOf("Received","Not Received","Pending")
        val deliveryStatus = arrayListOf("Delivered","Not Delivered","Delivered")
        val adapter = DeliveryAdapter(customerName,paymentStatus,deliveryStatus)
        binding.deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.deliveryRecyclerView.adapter = adapter

        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}