package com.example.adminpanelfoodontheway

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodontheway.databinding.ActivityAddMenuBinding
import com.example.adminpanelfoodontheway.databinding.ActivityAdminProfileBinding

class AdminProfile : AppCompatActivity() {
    private val binding : ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener{
            finish()
        }
        binding.nameProfile.isEnabled = false
        binding.addressProfile.isEnabled = false
        binding.emailProfile.isEnabled = false
        binding.phoneProfile.isEnabled = false
        binding.passwordProfile.isEnabled = false

        var isEnable=false
        binding.editButton.setOnClickListener{
            isEnable=!isEnable
            binding.nameProfile.isEnabled = isEnable
            binding.addressProfile.isEnabled = isEnable
            binding.emailProfile.isEnabled = isEnable
            binding.phoneProfile.isEnabled = isEnable
            binding.passwordProfile.isEnabled = isEnable
            if(isEnable==true){
                binding.nameProfile.requestFocus()
            }
        }
        binding.saveButton.setOnClickListener{
            binding.nameProfile.isEnabled = false
            binding.addressProfile.isEnabled = false
            binding.emailProfile.isEnabled = false
            binding.phoneProfile.isEnabled = false
            binding.passwordProfile.isEnabled = false
        }


    }
}