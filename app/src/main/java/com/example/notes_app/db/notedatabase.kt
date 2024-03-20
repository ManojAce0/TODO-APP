package com.example.notes_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


    @Database(entities = [note::class], version = 1, exportSchema = false)
    abstract class notedatabase : RoomDatabase() {

        abstract fun notedao(): notedao

        companion object {
            @Volatile
            private var INSTANCE: notedatabase? = null

            fun getInstance(context: Context): notedatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        notedatabase::class.java,
                        "note_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
