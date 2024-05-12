package com.example.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagement.databinding.ActivityMainBinding
import android.widget.SearchView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NoteDatabaseHelper
    private lateinit var notesAdapter: NotesAdapters
    private var notesList: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        notesList.addAll(db.getAllNotes())
        notesAdapter = NotesAdapters(notesList, this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivity(intent)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filterNotes(it)
                }
                return true
            }
        })
    }

    private fun filterNotes(query: String) {
        val filteredList = db.searchNotes(query)
        notesList.clear()
        notesList.addAll(filteredList)
        notesAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        notesList.clear()
        notesList.addAll(db.getAllNotes())
        notesAdapter.notifyDataSetChanged()
    }
}
