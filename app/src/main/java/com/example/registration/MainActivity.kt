package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.registration.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val firstname=binding.edtfirstname.text.toString()
            val lastname=binding.edtlastname.text.toString()
            val age=binding.edtage.text.toString()
            val username=binding.edtusername.text.toString()


            database=FirebaseDatabase.getInstance().getReference("Users")
            val user=User(firstname, lastname, age, username)
            database.child(username).setValue(user).addOnSuccessListener {
                binding.edtfirstname.text.clear()
                binding.edtlastname.text.clear()
                binding.edtage.text.clear()
                binding.edtusername.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed to Create",Toast.LENGTH_SHORT).show()
            }
        }
    }
}