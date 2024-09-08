package com.example.adminpanelfoodontheway

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodontheway.databinding.ActivityAddMenuBinding
import com.example.adminpanelfoodontheway.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.addMenu.setOnClickListener{
            val intent = Intent(this,AddMenuActivity::class.java)
            startActivity(intent)
        }
        binding.allItemMenu.setOnClickListener{
            val intent = Intent(this,AllMenuActivity::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener{
            val intent = Intent(this,AdminProfile::class.java)
            startActivity(intent)
        }
        binding.createUser.setOnClickListener{
            val intent = Intent(this,CreateUser::class.java)
            startActivity(intent)
        }
        binding.orderDispatch.setOnClickListener{
            val intent = Intent(this,OutForDeliveryActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.PendingOrders.setOnClickListener{
            val intent = Intent(this,PendingOrders::class.java)
            startActivity(intent)
        }


    }
}