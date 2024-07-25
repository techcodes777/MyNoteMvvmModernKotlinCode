package com.eclatsol.mynotemvvmmodernkotlincode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo("title") var title: String,
    @ColumnInfo("description") var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}