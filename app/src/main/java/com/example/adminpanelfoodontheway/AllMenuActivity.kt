package com.example.adminpanelfoodontheway

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodontheway.adapter.MenuItemAdapter
import com.example.adminpanelfoodontheway.databinding.ActivityAllMenuBinding
import com.example.adminpanelfoodontheway.model.AddMenu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllMenuActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var menuItems : ArrayList<AddMenu> = ArrayList()
    private val binding : ActivityAllMenuBinding by lazy {
        ActivityAllMenuBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        retrieveMenuItem()
        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun retrieveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef : DatabaseReference = database.reference.child("menu")

        //fetch data from Database
        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                //clear existing data before Population
                menuItems.clear()

                //for loop through each food Item
                for(foodSnapshot in snapshot.children){
                    val menuItem = foodSnapshot.getValue(AddMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                //set Adapter
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Database Error","Error ${error.message}")
            }
        })

    }

    private fun setAdapter() {
        val adapter = MenuItemAdapter(this, menuItems,databaseReference)
        binding.allItemRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.allItemRecyclerView.adapter = adapter
    }
}