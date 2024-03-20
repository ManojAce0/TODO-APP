package com.example.notes_app

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_app.databinding.NoteItemBinding
import com.example.notes_app.db.note

class recyclerviewadapter(private val onUpdateClick: (note: note) -> Unit,private val ondeleteclick: (note: note) -> Unit) : RecyclerView.Adapter<recyclerviewadapter.noteviewholder>() {
    private val notelist = ArrayList<note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteviewholder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return noteviewholder(binding)
    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    override fun onBindViewHolder(holder: noteviewholder, position: Int) {
        holder.bind(notelist[position])
        holder.binding.updatebutton.setOnClickListener {
            // Pass the selected note details to the addnotes activity
            onUpdateClick(notelist[position])
        }
        holder.binding.deletebutton.setOnClickListener {
            ondeleteclick(notelist[position])
        }
    }
    fun setList(list: List<note>) {
        notelist.clear()
        notelist.addAll(list)
        Log.d("Adapter", "Set list size: ${notelist.size}")
        notifyDataSetChanged()
    }

    class noteviewholder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: note) {
            binding.apply {
                titletextview.text = note.title
                contenttextview.text = note.content
            }
        }
    }
}

