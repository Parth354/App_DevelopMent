package com.example.notetakingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapplication.ViewModel.NoteViewModel
import com.example.notetakingapplication.ViewModel.ViewModelFactory
import com.example.notetakingapplication.database.NoteDatabase
import com.example.notetakingapplication.databinding.ActivityMainBinding
import com.example.notetakingapplication.repository.NoteRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository=NoteRepository(NoteDatabase(this))
        val viewProviderFactory =ViewModelFactory(application,noteRepository)
        noteViewModel=ViewModelProvider(
            this,viewProviderFactory
        ).get(NoteViewModel::class.java)
    }
}