package com.kluivert.mininoteapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kluivert.mininoteapp.data.database.NoteDatabase
import com.kluivert.mininoteapp.data.entities.NoteModel
import com.kluivert.mininoteapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val readNote : LiveData<List<NoteModel>>

    val noteRepo : NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        noteRepo = NoteRepository(noteDao)
        readNote = noteDao.readNote()
    }


    fun addNote(noteModel: NoteModel){
        viewModelScope.launch (Dispatchers.IO){
            noteRepo.addNote(noteModel)
        }
    }

    fun updateNote(noteModel: NoteModel){
        viewModelScope.launch (Dispatchers.IO){
            noteRepo.updateNote(noteModel)

        }
    }

    fun deleteNote(noteModel: NoteModel){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.deleteNote(noteModel)
        }
    }

    fun deleteAllNote(){
        viewModelScope.launch (Dispatchers.IO){
            noteRepo.deleteAllNote()
        }
    }

}