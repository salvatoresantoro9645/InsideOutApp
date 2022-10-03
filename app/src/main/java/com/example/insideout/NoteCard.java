package com.example.insideout;

import java.util.Date;

public class NoteCard {

    public String noteTitle; //Title == noteId + mood
    public String noteDateTime; //dd-MM-yyyy HH:mm:ss
    public String noteText; //to scroll-down

    public boolean visibility;

    public NoteCard(String noteTitle, String noteDateTime, String noteText){
        this.noteTitle = noteTitle;
        this.noteDateTime = noteDateTime;
        this.noteText = noteText;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDateTime() {
        return noteDateTime;
    }

    public String getNoteText() {
        return noteText;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}
