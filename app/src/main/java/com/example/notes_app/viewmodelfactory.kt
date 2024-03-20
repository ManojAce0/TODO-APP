package com.example.notes_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes_app.db.notedao
import com.example.notes_app.viewmodelnotes


class viewmodelfactory(private val notedao: notedao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewmodelnotes::class.java)) {
            return viewmodelnotes(notedao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}