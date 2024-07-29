package com.example.uas_mobile_programming

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etUsernameReg: EditText
    private lateinit var etPasswordReg: EditText
    private lateinit var btnRegister: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.etEmail)
        etUsernameReg = findViewById(R.id.etUsernameReg)
        etPasswordReg = findViewById(R.id.etPasswordReg)
        btnRegister = findViewById(R.id.btnRegister)

        // Initialize database reference
        database = FirebaseDatabase.getInstance().reference.child("users")

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val username = etUsernameReg.text.toString()
            val password = etPasswordReg.text.toString()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Semua field wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Store data to Firebase
                database.child(username).child("email").setValue(email)
                database.child(username).child("username").setValue(username)
                database.child(username).child("password").setValue(password)

                Toast.makeText(applicationContext, "Register berhasil", Toast.LENGTH_SHORT).show()
                val register = Intent(applicationContext, Login::class.java)
                startActivity(register)
            }
        }
    }
}
