package com.example.contactmanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanager.repository.Contact_Repo


class ViewModelFactory(private val repository : Contact_Repo) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}