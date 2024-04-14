package com.example.notetakingapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notetakingapplication.ViewModel.NoteViewModel
import com.example.notetakingapplication.adapter.NoteAdapter
import com.example.notetakingapplication.databinding.FragmentHomeBinding
import com.example.notetakingapplication.databinding.FragmentUpdateBinding
import com.example.notetakingapplication.model.Note


class UpdateFragment : Fragment(R.layout.fragment_update) {
    private var _binding: FragmentUpdateBinding?=null
    private val binding get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var currentNote:Note
    //Since the Update Note Fragment contains arguments in nav_graph we need to create private val args
    private val args:UpdateFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel=(activity as MainActivity).noteViewModel
        currentNote=args.note!!

        binding.NoteTitleUpdate.setText(currentNote.noteTitle)
        binding.NoteBodyUpdate.setText(currentNote.noteBody)

        //user update
        binding.fabAddNote.setOnClickListener{
            val title=binding.NoteTitleUpdate.text.toString().trim()
            val body=binding.NoteBodyUpdate.text.toString().trim()

            if(title.isNotEmpty()){
                val note=Note(currentNote.id,title,body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }else{
                Toast.makeText(context,"Please Enter Title",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteNode(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Node")
            setMessage("Are you sure? You want to delete")
            setPositiveButton("Yes") { _, _ ->
                noteViewModel.deleteNote(currentNote)

                view?.findNavController()?.navigate(
                    R.id.action_updateFragment_to_homeFragment
                )
            }
                setNegativeButton("No",null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete->{
                deleteNode()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}