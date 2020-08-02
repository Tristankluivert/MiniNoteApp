package com.kluivert.mininoteapp.data.repository

import androidx.lifecycle.LiveData
import com.kluivert.mininoteapp.data.dao.NoteDao
import com.kluivert.mininoteapp.data.entities.NoteModel

class NoteRepository (val noteDao: NoteDao){

    val readNote : LiveData<List<NoteModel>> = noteDao.readNote()

    suspend fun addNote(noteModel: NoteModel){
        noteDao.addNote(noteModel)
    }

    suspend fun deleteNote(noteModel: NoteModel){
        noteDao.deleteNote(noteModel)
    }

    suspend fun updateNote(noteModel: NoteModel){
        noteDao.updateNote(noteModel)
    }

    suspend fun deleteAllNote(){
        noteDao.deleteAllNotes()
    }



}
