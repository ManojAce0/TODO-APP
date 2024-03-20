package com.example.notes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_app.databinding.ActivityMainBinding
import com.example.notes_app.db.note
import com.example.notes_app.db.notedatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:recyclerviewadapter
    private lateinit var viewmodel:viewmodelnotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = notedatabase.getInstance(application).notedao()
        val factory = viewmodelfactory(dao)

        viewmodel= ViewModelProvider(this, factory)[viewmodelnotes::class.java]
        binding.addnotesbutton.setOnClickListener{
            val intent=Intent(this,addnotes::class.java)
            startActivity(intent)
        }
        binding.rvnote.layoutManager = LinearLayoutManager(this)
        adapter = recyclerviewadapter (::handleUpdateClick,::handledeleteclick)

        binding.rvnote.adapter = adapter

        // In MainActivity
        viewmodel.notes.observe(this) {
            Log.d("MainActivity", "Observed notes size: ${it.size}")
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }
    private fun handleUpdateClick(note: note) {
        val intent = Intent(this, addnotes::class.java)
        intent.putExtra("selectedNote", note)
        startActivity(intent)
    }
    private fun handledeleteclick(note: note){
        viewmodel.deletenote(note)
        }

    }