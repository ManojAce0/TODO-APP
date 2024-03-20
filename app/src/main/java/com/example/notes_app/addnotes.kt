package com.example.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notes_app.databinding.ActivityAddnotesBinding
import com.example.notes_app.db.note
import com.example.notes_app.db.notedatabase

class addnotes : AppCompatActivity() {
    private lateinit var viewmodel: viewmodelnotes
    private lateinit var binding: ActivityAddnotesBinding
    private var selectedNote: note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddnotesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dao = notedatabase.getInstance(application).notedao()
        val factory = viewmodelfactory(dao)
        viewmodel = ViewModelProvider(this, factory).get(viewmodelnotes::class.java)


        // Receive selected note from intent extras
        selectedNote = intent.getSerializableExtra("selectedNote") as? note
        val action = intent.getStringExtra("action")

        if (selectedNote != null) {

            binding.apply {
                titledittext.setText(selectedNote?.title)
                contentedittext.setText(selectedNote?.content)
                donebutton.setOnClickListener {
                    updateNote()
                    finish()
                }
            }
        } else {
            binding.apply {
                donebutton.setOnClickListener {
                    insertNote()
                    finish()
                }
            }
        }
    }

    private fun insertNote() {
        binding.apply {
            viewmodel.insertnote(
                note(
                    0,
                    titledittext.text.toString(),
                    contentedittext.text.toString()
                )
            )
        }
    }

    private fun updateNote() {
        binding.apply {
            viewmodel.updatenote(
                note(
                    selectedNote?.id ?: 0,  // Use the existing note ID
                    titledittext.text.toString(),
                    contentedittext.text.toString()
                )
            )
        }
    }
}