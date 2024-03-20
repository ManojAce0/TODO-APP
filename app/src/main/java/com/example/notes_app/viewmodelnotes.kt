package com.example.notes_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes_app.db.note
import com.example.notes_app.db.notedao
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class viewmodelnotes(private val notedao: notedao): ViewModel() {
    val notes: LiveData<List<note>> = notedao.getallnotes()

        fun insertnote(note: note)=viewModelScope.launch {
            notedao.insertnote(note)
        }
        fun updatenote(note: note)=viewModelScope.launch {
            notedao.updatenote(note)
        }
        fun deletenote(note: note)=viewModelScope.launch {
            notedao.deletenote(note)
            }
    }
