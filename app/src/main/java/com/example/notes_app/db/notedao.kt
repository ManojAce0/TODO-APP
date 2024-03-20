package com.example.notes_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface notedao {

    @Insert
    suspend fun insertnote(note:note)

    @Update
    suspend fun updatenote(note: note)

    @Delete
    suspend fun deletenote(note:note)

    @Query("SELECT * FROM notes_data_table" )
    fun getallnotes():LiveData<List<note>>

}