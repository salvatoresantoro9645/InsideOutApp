package com.example.insideout;

public class FilmCard {

    private int filmImage;
    private String filmTitle;
    private String filmGenre;
    private String filmTrailerLink;

    public FilmCard(int filmImage, String filmTitle, String filmGenre, String filmTrailerLink){
        this.filmImage = filmImage;
        this.filmTitle = filmTitle;
        this.filmGenre = filmGenre;
        this.filmTrailerLink = filmTrailerLink;
    }

    public int getFilmImage() {
        return filmImage;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public String getFilmTrailerLink() {
        return filmTrailerLink;
    }
}
