package com.example.notetakingapplication.repository

import com.example.notetakingapplication.database.NoteDatabase
import com.example.notetakingapplication.model.Note

class NoteRepository(private val db:NoteDatabase) {

    suspend fun insertNote(note:Note)=db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note)=db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note)=db.getNoteDao().updateNote(note)

    fun getAllNotes()=db.getNoteDao().getAllNotes()
    fun searchNotes(query:String?)=db.getNoteDao().searchNote(query)
}