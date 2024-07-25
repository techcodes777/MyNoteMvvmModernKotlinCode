package com.eclatsol.mynotemvvmmodernkotlincode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository: NoteRepository
     var allNoteData: LiveData<List<Note>>

    init {
        val noteDao = NoteDataBase.getInstance(application.applicationContext)!!.getNoteDao()
        noteRepository = NoteRepository(noteDao)
        allNoteData = noteRepository.allNoteData
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(note)
    }
}