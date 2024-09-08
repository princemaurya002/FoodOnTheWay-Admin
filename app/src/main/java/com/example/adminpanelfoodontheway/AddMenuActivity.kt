package com.example.adminpanelfoodontheway

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodontheway.databinding.ActivityAddMenuBinding
import com.example.adminpanelfoodontheway.model.AddMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddMenuActivity : AppCompatActivity() {

    //Food Item Details
    private lateinit var  foodName:String
    private lateinit var  foodPrice:String
    private var  foodImageUri: Uri?=null
    private lateinit var  foodDescription:String
    private lateinit var  foodIngredients:String
    //FireBase
    private lateinit var auth: FirebaseAuth
    private lateinit var database:FirebaseDatabase


    private val binding : ActivityAddMenuBinding by lazy {
        ActivityAddMenuBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.addItem.setOnClickListener{
            //Get data from every field
            foodName = binding.itemName.text.toString().trim()
            foodPrice = binding.itemPrice.text.toString().trim()
            foodDescription = binding.shortDescription.text.toString().trim()
            foodIngredients = binding.ingredients.text.toString().trim()

            if(!(foodName.isBlank()||foodPrice.isBlank()||foodDescription.isBlank()||foodIngredients.isBlank())){
                uploadData()
                Toast.makeText(this, "Item added Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this, "Please Fill All the Details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.selectImage.setOnClickListener{
            pickImage.launch("image/*")
        }

    }

    private fun uploadData() {
        //get a reference to the menu
        val MenuRef = database.getReference("menu")
        //Generate Unique Key for the New Menu
        val newItemKey = MenuRef.push().key

        if(foodImageUri!=null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    //Create a menu item
                    val newItem = AddMenu(
                        foodName=foodName,
                        foodPrice=foodPrice,
                        foodDescription = foodDescription,
                        foodIngredients = foodIngredients,
                        foodImage = downloadUrl.toString(),
                        foodItemID = newItemKey
                    )
                    newItemKey?.let {
                        key->
                        MenuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Toast.makeText(this, "Item added Successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener{
                            Toast.makeText(this, "Item not Uploaded", Toast.LENGTH_SHORT).show()
                        }
                    }
                }.addOnFailureListener{
                    Toast.makeText(this, "Image not Uploaded", Toast.LENGTH_SHORT).show()

                }
            }

        }
        else{
            Toast.makeText(this, "Image not Selected", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    binding.selectedImage.setImageURI(uri)
                    foodImageUri=uri
                }
            }
}