package com.eclatsol.mynotemvvmmodernkotlincode

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    //Dao interface hoi che ka to abstract hoi che
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert  (note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table order by id ASC")
    fun getAllData(): LiveData<List<Note>>
}