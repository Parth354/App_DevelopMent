package com.example.contactmanager.repository


import com.example.contactmanager.Room.Contacts
import com.example.contactmanager.Room.DAO

class Contact_Repo(private var contactDAO: DAO) {
    val contacts =contactDAO.getAllContacts()

    suspend fun insert(contacts:Contacts):Long{
        return contactDAO.insertContact(contacts)
    }

    suspend fun delete(contacts: Contacts){
        return contactDAO.deleteContact(contacts)
    }
    suspend fun update(contacts: Contacts){
        return contactDAO.updateContact(contacts)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}