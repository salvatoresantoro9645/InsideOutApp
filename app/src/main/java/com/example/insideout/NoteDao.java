package com.example.insideout;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;

@Dao
public interface NoteDao {

    @Insert
    public void insertNotes(Note... notes);

    @Query("SELECT * FROM note WHERE authorId == :reqAuthorId AND date == :reqDate") //to fix the date for that day
    public Note[] loadAllNotesForReqDate(String reqAuthorId, String reqDate);

    @Query("SELECT * FROM note")
    public Note[] loadAllNotes();

    @Update
    public void updateNotes(Note... notes);

    @Delete
    public void deleteNotes(Note... notes);

    @Delete
    public void deleteAllNotes(Note... notes);
}
