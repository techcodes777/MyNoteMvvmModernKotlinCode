package com.eclatsol.mynotemvvmmodernkotlincode

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNoteData: LiveData<List<Note>> = noteDao.getAllData()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}