package com.example.insideout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MoodActivity extends AppCompatActivity{

    private RecyclerView actionRecyclerView;
    private ActionCardAdapter actionCardAdapter;
    private List<ActionCard> actionCardList;
    private String mood;
    private TextView textMood;
    private TextView textQuote;
    private Toolbar titlebar;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mood = bundle.getString("MoodTitle");
        }

        context = this;

        setTitlebarBackButton(); //set back button for this activity

        initializeCardView(); //initialize the cardView
        textMood = (TextView) findViewById(R.id.moodText_activity_mood);
        textQuote = (TextView) findViewById(R.id.textView_quotes);
        textMood.setText(this.mood+" MOOD");
        setTextQuote(); //set the right quote in textQuote (based on mood)
        View rootView = actionRecyclerView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.my_green_200));
    }

    private void setTitlebarBackButton(){
        titlebar = (Toolbar) findViewById(R.id.titleToolbar);
        setSupportActionBar(titlebar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_36);
        titlebar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MoodSelectionActivity.super.onBackPressed();
                Intent CardViewActivity = new Intent(context, MoodSelectionActivity.class);
                //Log.d("INTENT","After Intent creation");
                startActivity(CardViewActivity);
                overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(context, MoodSelectionActivity.class);
        //Log.d("INTENT","After Intent creation");
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
    }

    private void initializeCardView(){
        actionRecyclerView = findViewById(R.id.action_recycleView);
        actionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        actionCardList = new ArrayList<ActionCard>();

        createDataForCards();
        actionCardAdapter = new ActionCardAdapter(this, actionCardList, mood);
        actionRecyclerView.setAdapter(actionCardAdapter);
    }

    private void createDataForCards(){ //TAKE THE EMOTION AND INSERT ONLY THE REQUIRED CARDS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ActionCard actionCard;

        actionCard = new ActionCard(R.mipmap.image_music_green, "MUSIC", "Listen Some Song");
        actionCardList.add(actionCard);

        actionCard = new ActionCard(R.mipmap.image_film_green, "FILM", "Watch Some Film"); //Trailer in reality
        actionCardList.add(actionCard);

        actionCard = new ActionCard(R.mipmap.image_gps_green, "GPS", "Visit Some Place");
        actionCardList.add(actionCard);

        actionCard = new ActionCard(R.mipmap.image_note_green, "NOTE", "Write some Note");
        actionCardList.add(actionCard);

        //actionCardAdapter.notifyDataSetChanged();  //in case of dynamic change
    }

    public void setTextQuote(){
        switch (this.mood){
            case "JOY":
                textQuote.setText(R.string.title_joy_quote);
                break;
            case "SORROW":
                textQuote.setText(R.string.title_sorrow_quote);
                break;
            case "ANGER":
                textQuote.setText(R.string.title_anger_quote);
                break;
            case "DISGUST":
                textQuote.setText(R.string.title_disgust_quote);
                break;
            case "FEAR":
                textQuote.setText(R.string.title_fear_quote);
                break;
            case "HUNGRY":
                textQuote.setText(R.string.title_hungry_quote);
                break;
        }
    }

}