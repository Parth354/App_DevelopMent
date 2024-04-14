package com.example.notetakingapplication

import android.os.Bundle
import com.example.notetakingapplication.model.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.example.notetakingapplication.ViewModel.NoteViewModel
import com.example.notetakingapplication.adapter.NoteAdapter
import com.example.notetakingapplication.databinding.FragmentNoteBinding


class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding?=null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var mView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel=(activity as MainActivity).noteViewModel
        mView=view

        }

    private fun saveNote(view:View){
        val noteTitle=binding.NoteTitle.text.toString().trim()
        val noteBody= binding.NoteBody.text.toString().trim()

        if(noteBody.isNotEmpty()){
            val note = Note(0, noteTitle, noteBody)

            noteViewModel.addNote(note)
            Toast.makeText(mView.context,"Note Saved Successfully!",Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_noteFragment_to_homeFragment)
        }else{
            Toast.makeText(mView.context,"Please Enter The Note Title",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}