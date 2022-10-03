package com.example.insideout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class FilmActivity extends ActionBaseActivity {

    private static final String MOOD_TITLE = "MoodTitle";

    private Context context;
    private RecyclerView filmRecyclerView;
    private FilmCardAdapter filmCardAdapter;
    private List<FilmCard> filmCardList;
    private String mood;  //value received in bundle to choose the playlist that should be create
    private MoodFilmListCreator moodFilmListCreator;
    private MaterialCardView selectedCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_music); using abstract method getLayoutResourceId
        context = this;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mood = bundle.getString(MOOD_TITLE);
        }

        if (savedInstanceState != null) {
            mood = savedInstanceState.getString(MOOD_TITLE);
        }

        moodFilmListCreator = new MoodFilmListCreator(mood);
        initializeCardView(); //initialize the cardView

        View rootView = filmRecyclerView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.my_green_200));
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_film;
    }

    private void initializeCardView(){
        filmRecyclerView = findViewById(R.id.film_recycleView);
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        filmCardList = new ArrayList<FilmCard>();

        createDataForCards();
        filmCardAdapter = new FilmCardAdapter(this, filmCardList);
        filmRecyclerView.setAdapter(filmCardAdapter);
    }

    private void createDataForCards(){
        filmCardList = moodFilmListCreator.createCardList(this.filmCardList, context);
        //filmCardAdapter.notifyDataSetChanged(); //in case of dynamic change
    }
}