package com.example.contactmanager.viewModel

import androidx.databinding.Bindable
import com.example.contactmanager.Room.Contacts
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanager.repository.Contact_Repo
import kotlinx.coroutines.launch

class ContactViewModel(private val repository :Contact_Repo):ViewModel(),Observable {

    val users= repository.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contacts

    //Data Binding With Live Data

    @Bindable
    val input_name =MutableLiveData<String?>()

    @Bindable
    val input_email = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateBtn =MutableLiveData<String?>()

    @Bindable
    val clearAllOrDeleteBtn=MutableLiveData<String?>()

    init {
        saveOrUpdateBtn.value="Save"
        clearAllOrDeleteBtn.value="Clear All"
    }

    fun insert(contacts: Contacts) = viewModelScope.launch {
        repository.insert(contacts)
    }
    fun delete(contacts: Contacts) = viewModelScope.launch {
        repository.delete(contacts)

        //Resetting the Buttons And The Fields
        input_name.value=null
        input_email.value=null
        isUpdateOrDelete=false
        saveOrUpdateBtn.value="Save"
        clearAllOrDeleteBtn.value="Clear All"

    }

    fun update(contacts: Contacts)=viewModelScope.launch {
        repository.update(contacts)
        input_name.value=null
        input_email.value=null
        isUpdateOrDelete=false
        saveOrUpdateBtn.value="Save"
        clearAllOrDeleteBtn.value="Clear All"
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            //make a update
            contactToUpdateOrDelete.name=input_name.value!!
            contactToUpdateOrDelete.email=input_email.value!!
            update(contactToUpdateOrDelete)
        }
        else{
            val name=input_name.value!!
            val email=input_email.value!!
            insert(Contacts(0,name,email))
            input_name.value=null
            input_email.value=null
        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(contactToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun initUpdateandDelete(contacts: Contacts){
        input_name.value=contacts.name
        input_email.value=contacts.email
        isUpdateOrDelete=true
        contactToUpdateOrDelete=contacts
        saveOrUpdateBtn.value="Update"
        clearAllOrDeleteBtn.value="Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}