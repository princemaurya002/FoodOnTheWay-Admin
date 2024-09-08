package com.example.adminpanelfoodontheway

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanelfoodontheway.databinding.ActivitySignupBinding
import com.example.adminpanelfoodontheway.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import kotlin.math.log

class SignupActivity : AppCompatActivity() {
    private lateinit var userName:String
    private lateinit var nameOfRestaurant:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth:FirebaseAuth
    private lateinit var database:DatabaseReference


    private val binding : ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialising Firebase Auth
        auth = Firebase.auth
        //Initialising Firebase Auth
        database=Firebase.database.reference

        binding.haveAccount.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.createAccountButton.setOnClickListener{
            //getting text from edittext
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()
            userName = binding.userName.text.toString().trim()
            nameOfRestaurant = binding.nameOfRestaurant.text.toString().trim()
            if(userName.isBlank() ||nameOfRestaurant.isBlank() ||email.isBlank() ||password.isBlank() ){
                Toast.makeText(this,"Please fill All Details",Toast.LENGTH_SHORT).show()
            }
            else{
                createAccount(email,password)

            }
        }
        val locationList = arrayOf("Jaipur","Indore","Gwalior","Delhi")
        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,locationList)
        val autoCompleteTextView = binding.locationList
        autoCompleteTextView.setAdapter(adapter)


    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                saveUserData(userName,nameOfRestaurant,email,password)
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount:Failure",task.exception)
            }

        }
    }

    private fun saveUserData(
        userName: String,
        nameOfRestaurant: String,
        email: String,
        password: String
    ) {
        val user = UserModel(userName,nameOfRestaurant,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        //saving database in firebase database
        database.child("user").child("seller").child(userId).setValue(user)
    }
}