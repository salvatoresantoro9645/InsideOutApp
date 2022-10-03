package com.example.insideout;

public class MusicCard {

    private int musicImage;
    private String musicTitle;
    private String musicAuthor;

    public MusicCard(int musicImage, String musicTitle, String musicAuthor){
        this.musicImage = musicImage;
        this.musicTitle = musicTitle;
        this.musicAuthor = musicAuthor;
    }

    public int getMusicImage() {
        return musicImage;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }
}