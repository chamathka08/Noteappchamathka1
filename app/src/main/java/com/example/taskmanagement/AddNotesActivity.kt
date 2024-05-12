package com.example.taskmanagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {
 private lateinit var binding:ActivityAddNotesBinding
 private lateinit var db:NoteDatabaseHelper

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

            binding.saveButton.setOnClickListener{
                val title = binding.titleEditText.text.toString()
                val content = binding.contentsText.text.toString()
                val note = Note(0, title, content)
                db.insertNote(note)
                finish()
                Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()

            }


    }
}