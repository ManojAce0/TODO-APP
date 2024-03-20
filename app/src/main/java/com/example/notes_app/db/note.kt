package com.example.notes_app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_data_table")
class note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Int,
    @ColumnInfo(name = "title_name")
    val title:String,
    @ColumnInfo(name = "content_text")
    val content:String ): Serializable
