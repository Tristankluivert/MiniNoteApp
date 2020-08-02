package com.kluivert.mininoteapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kluivert.mininoteapp.data.entities.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun readNote(): LiveData<List<NoteModel>>


}