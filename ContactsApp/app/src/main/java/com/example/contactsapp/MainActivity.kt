package com.example.contactsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.ui.ContactAdapter
import com.example.contactsapp.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewContacts)
        val adapter = ContactAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Παρακολούθηση επαφών από ViewModel
        contactViewModel.allContacts.observe(this) { contacts ->
            adapter.setContacts(contacts)
        }

        // Πράσινο κουμπί προσθήκης επαφής
        val buttonAddContact = findViewById<Button>(R.id.buttonAddContact)
        buttonAddContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
    }
}
