package com.example.contactsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import com.example.contactsapp.data.entity.ContactEntity
import com.example.contactsapp.viewmodel.ContactViewModel
import com.example.contactsapp.R

class AddContactActivity : AppCompatActivity() {

    private val contactViewModel: ContactViewModel by viewModels()

    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editPhone: EditText
    private lateinit var editEmail: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_contact)

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_contact_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Views
        editFirstName = findViewById(R.id.editFirstName)
        editLastName = findViewById(R.id.editLastName)
        editPhone = findViewById(R.id.editPhone)
        editEmail = findViewById(R.id.editEmail)
        buttonSave = findViewById(R.id.buttonSave)

        // Κουμπί αποθήκευσης
        buttonSave.setOnClickListener {
            val firstName = editFirstName.text.toString().trim()
            val lastName = editLastName.text.toString().trim()
            val phone = editPhone.text.toString().trim()
            val email = editEmail.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contact = ContactEntity(
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                email = email
            )

            contactViewModel.insert(contact)
            Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show()
            finish() // κλείνει το activity και επιστρέφει στο MainActivity
        }
    }
}
