package com.eclatsol.mynotemvvmmodernkotlincode

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao


    companion object {
        @Volatile
        private var INTANCE: NoteDataBase? = null

        fun getInstance(context: Context) : NoteDataBase? {
            return INTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java, "NoteDatabase"
                ).fallbackToDestructiveMigration().build()

                INTANCE = instance
                INTANCE
            }

        }
    }
}