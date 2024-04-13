package com.example.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.viewModel.ContactViewModel
import com.example.contactmanager.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanager.Room.Contact_DB
import com.example.contactmanager.Room.Contacts
import com.example.contactmanager.viewModel.ViewModelFactory
import com.example.contactmanager.repository.Contact_Repo
import com.example.contactmanager.view.MyCustomView

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //ROOM DataBase
        val dao=Contact_DB.getInstance(applicationContext).contactDAO
        val repository = Contact_Repo(dao)
        val factory =ViewModelFactory(repository)

        //View Model
        contactViewModel=ViewModelProvider(this,factory).get(ContactViewModel::class.java)

        binding.contactViewModel=contactViewModel
        binding.lifecycleOwner=this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.RecyclerView.layoutManager=LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        contactViewModel.users.observe(this, Observer{
            binding.RecyclerView.adapter=MyCustomView(it) { selectedItem: Contacts ->
                listItemClicked(
                    selectedItem
                )
            }
        })
    }

    private fun listItemClicked(selectedItem: Contacts) {
        Toast.makeText(this,"Selected item ${selectedItem.name}",Toast.LENGTH_LONG).show()
        contactViewModel.initUpdateandDelete(selectedItem)
    }
}