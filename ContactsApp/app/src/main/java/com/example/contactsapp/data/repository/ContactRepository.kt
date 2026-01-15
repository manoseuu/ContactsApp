package com.example.contactsapp.data.repository

import com.example.contactsapp.data.dao.ContactDao
import com.example.contactsapp.data.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    val allContacts: Flow<List<ContactEntity>> =
        contactDao.getAllContacts()

    suspend fun insert(contact: ContactEntity) {
        contactDao.insert(contact)
    }

    suspend fun update(contact: ContactEntity) {
        contactDao.update(contact)
    }

    suspend fun delete(contact: ContactEntity) {
        contactDao.delete(contact)
    }
}
