package com.example.contactsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.data.entity.ContactEntity

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contacts: List<ContactEntity> = emptyList()

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.textName)
        val textPhone: TextView = itemView.findViewById(R.id.textPhone)
        val textEmail: TextView = itemView.findViewById(R.id.textEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.textName.text = "${contact.firstName} ${contact.lastName}"
        holder.textPhone.text = contact.phone
        holder.textEmail.text = contact.email
    }

    fun setContacts(newContacts: List<ContactEntity>) {
        contacts = newContacts
        notifyDataSetChanged()
    }
}
