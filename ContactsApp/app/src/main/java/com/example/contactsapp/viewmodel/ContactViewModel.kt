package com.example.contactsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.contactsapp.data.database.AppDatabase
import com.example.contactsapp.data.entity.ContactEntity
import com.example.contactsapp.data.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository

    val allContacts = AppDatabase
        .getDatabase(application)
        .contactDao()
        .getAllContacts()
        .asLiveData()

    init {
        val dao = AppDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(dao)
    }

    fun insert(contact: ContactEntity) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun update(contact: ContactEntity) = viewModelScope.launch {
        repository.update(contact)
    }

    fun delete(contact: ContactEntity) = viewModelScope.launch {
        repository.delete(contact)
    }
}
