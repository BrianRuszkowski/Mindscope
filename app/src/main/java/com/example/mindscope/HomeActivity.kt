package com.example.mindscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.mindscope.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var buttonAddNote: Button
    private lateinit var listViewNotes: ListView

    private lateinit var notesAdapter: ArrayAdapter<String>
    private lateinit var notesList: ArrayList<String>

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // logout button
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // show buttons
        editTextNote = findViewById(R.id.editTextNote)
        buttonAddNote = findViewById(R.id.buttonAddNote)
        listViewNotes = findViewById(R.id.listViewNotes)

        // show note list
        notesList = ArrayList()
        notesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notesList)
        listViewNotes.adapter = notesAdapter

        // Add note button
        buttonAddNote.setOnClickListener {
            val note = editTextNote.text.toString().trim()
            if (note.isNotEmpty()) {
                addNoteToList(note)
                clearNoteInput()
            }
        }

        // remove notes from list
        listViewNotes.setOnItemClickListener { _, _, position, _ ->
            removeNoteFromList(position)
        }
    }

    // add notes to the note table
    private fun addNoteToList(note: String) {
        notesList.add(note)
        notesAdapter.notifyDataSetChanged()
    }

    // remove notes from table upon a click
    private fun removeNoteFromList(position: Int) {
        if (position >= 0 && position < notesList.size) {
            notesList.removeAt(position)
            notesAdapter.notifyDataSetChanged()
        }
    }

    private fun clearNoteInput() {
        editTextNote.text.clear()
    }
}