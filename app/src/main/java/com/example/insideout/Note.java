package com.example.insideout;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int noteId;

    public String date; //dd-MM-yyyy

    public String text;

    public String authorId;

    public String authorName;

    public String mood;

    public String dateTime; //dd-MM-yyyy HH:mm:ss


    //Getters
    public int getNoteId() {
        return noteId;
    }

    public String getDate() {
        return this.date;
    }

    public String getText() {
        return text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getMood() {
        return mood;
    }

    public String getDateTime() {
        return dateTime;
    }


    //Setters
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
