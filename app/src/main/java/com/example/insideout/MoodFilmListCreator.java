package com.example.insideout;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;

import java.util.ArrayList;
import java.util.List;

public class MoodFilmListCreator {

    private String mood;

    public MoodFilmListCreator(String mood){
        this.mood = mood;
    }

    //return a list of FilmCard to use inside MusicActivity's RecycleView
    //and FilmCardAdapter's FilmCardHolder
    public List<FilmCard> createCardList(List<FilmCard> cardItems, Context context){
        switch(this.mood){
            case "JOY":
                cardItems = getJoyCardList(cardItems, context);
                break;
            case "SORROW":
                cardItems = getSorrowCardList(cardItems, context);
                break;
            case "ANGER":
                cardItems = getAngerCardList(cardItems, context);
                break;
            case "DISGUST":
                cardItems = getDisgustCardList(cardItems, context);
                break;
            case "FEAR":
                cardItems = getFearCardList(cardItems, context);
                break;
            case "HUNGRY":
                cardItems = getHungryCardList(cardItems, context);
                break;
        }
        return cardItems;
    }

    public List<FilmCard> getJoyCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_joy_yes_man);
        card = new FilmCard(R.drawable.joy_yes_man, "Yes Man", "Commedia romantica", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_joy_ti_presento_i_miei);
        card = new FilmCard(R.drawable.joy_ti_presento_i_miei, "Ti presento i miei", "Commedia Romantica", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_joy_sole_a_catinelle);
        card = new FilmCard(R.drawable.joy_sole_a_catinelle, "Sole a catinelle", "Commedia", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_joy_una_notte_sa_leoni);
        card = new FilmCard(R.drawable.joy_una_notte_da_leoni, "Una notte da leoni", "Commedia", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_joy_una_settimana_da_dio);
        card = new FilmCard(R.drawable.joy_una_settimana_da_dio, "Una settimana da Dio", "Commedia/Drammatico", trailerLink);
        cardItems.add(card);

        return cardItems;
    }


    public List<FilmCard> getSorrowCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_sorrow_la_ricerca_della_felicita);
        card = new FilmCard(R.drawable.sorrow_la_ricerca_della_felicita, "La ricerca della felicità", "Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_sorrow_hachiko);
        card = new FilmCard(R.drawable.sorrow_hachiko, "Hachiko", "Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_sorrow_titanic);
        card = new FilmCard(R.drawable.sorrow_titanic, "Titanic", "Romantico/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_sorrow_le_pagine_della_nostra_vita);
        card = new FilmCard(R.drawable.sorrow_le_pagine_della_nostra_vita, "Le pagine della nostra vita", "Romantico/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_sorrow_shindler_s_list);
        card = new FilmCard(R.drawable.sorrow_schindler_s_list, "Schindler's list", "Guerra/Drammatico", trailerLink);
        cardItems.add(card);

        return cardItems;
    }


    public List<FilmCard> getAngerCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_anger_terapia_d_urto);
        card = new FilmCard(R.drawable.anger_terapia_d_urto, "Terapia d'urto", "Commedia/Buddy", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_anger_never_back_down);
        card = new FilmCard(R.drawable.anger_never_back_down, "Never back down", "Azione/Thriller/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_anger_fight_club);
        card = new FilmCard(R.drawable.anger_fight_club, "Fight club", "Azione/Thriller/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_anger_conspiracy);
        card = new FilmCard(R.drawable.anger_conspiracy, "Conspiracy", "Thriller/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_anger_la_belva);
        card = new FilmCard(R.drawable.anger_la_belva, "La belva", "Thriller/Drammatico", trailerLink);
        cardItems.add(card);

        return cardItems;
    }


    public List<FilmCard> getDisgustCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_disgust_perfetti_sconosciuti);
        card = new FilmCard(R.drawable.disgust_perfetti_sconosciuti, "Perfetti sconosciuti", "Commedia drammatica", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_disgust_django_unchained);
        card = new FilmCard(R.drawable.disgust_django_unchained, "Django unchained", "Western/Azione/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_disgust_l_amore_infedele);
        card = new FilmCard(R.drawable.disgust_l_amore_infedele, "L'amore infedele", "Romantico/Thriller/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_disgust_il_buco);
        card = new FilmCard(R.drawable.disgust_il_buco, "Il buco", "Thriller/Horror/Fantascienza", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_disgust_the_wolf_of_wall_street);
        card = new FilmCard(R.drawable.disgust_the_wolf_of_wall_street, "The wolf of wall street", "Commedia/Drammatico", trailerLink);
        cardItems.add(card);

        return cardItems;
    }


    public List<FilmCard> getFearCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_fear_the_conjuring);
        card = new FilmCard(R.drawable.fear_the_conjuring, "The conjuring", "Thriller/Horror", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_fear_the_shining);
        card = new FilmCard(R.drawable.fear_the_shining, "The Shining", "Thriller/Horror", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_fear_non_aprite_quella_porta);
        card = new FilmCard(R.drawable.fear_non_aprite_quella_porta, "Non aprite quella porta", "Thriller/Horror", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_fear_profondo_rosso);
        card = new FilmCard(R.drawable.fear_profondo_rosso, "Profondo rosso", "Thriller/Horror", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_fear_la_mosca);
        card = new FilmCard(R.drawable.fear_la_mosca, "La mosca", "Horror/Fantascienza/Drammatico", trailerLink);
        cardItems.add(card);

        return cardItems;
    }


    public List<FilmCard> getHungryCardList(List<FilmCard> cardItems, Context context){
        FilmCard card = null;
        String trailerLink = "";

        trailerLink = context.getString(R.string.trailer_hungry_piovono_polpette);
        card = new FilmCard(R.drawable.hungry_piovono_polpette, "Piovono polpette", "Animazione/Commedia/Fantascienza", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_hungry_chef_la_ricetta_perfetta);
        card = new FilmCard(R.drawable.hungry_chef_la_ricetta_perfetta, "Chef la ricetta perfetta", "Avventura/Commedia drammatica", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_hungry_il_sapore_del_successo);
        card = new FilmCard(R.drawable.hungry_il_sapore_del_successo, "Il sapore del successo", "Commedia/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_hungry_ratatouille);
        card = new FilmCard(R.drawable.hungry_ratatouille, "Ratatouille", "Animazione/Commedia/Drammatico", trailerLink);
        cardItems.add(card);
        trailerLink = context.getString(R.string.trailer_hungry_la_fabbrica_di_cioccolato);
        card = new FilmCard(R.drawable.hungry_la_fabbrica_di_cioccolato, "La fabbrica di cioccolato", "Commedia/Avventura", trailerLink);
        cardItems.add(card);

        return cardItems;
    }

}

