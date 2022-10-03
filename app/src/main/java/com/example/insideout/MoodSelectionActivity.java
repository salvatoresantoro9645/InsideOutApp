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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MoodSelectionActivity extends AppCompatActivity {

    private RecyclerView moodRecyclerView;
    private CardAdapter cardAdapter;
    private List<MoodCard> moodCardList;
    private Toolbar titlebar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_selection);
        context = this;

        setTitlebarBackButton(); //set back button for this activity

        initializeCardView(); //ititialize the cardView
        View rootView = moodRecyclerView.getRootView();
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
                Intent CardViewActivity = new Intent(context, HomeActivity.class);
                //Log.d("INTENT","After Intent creation");  //Debug
                startActivity(CardViewActivity);
                overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
            }
        });
    }

    public void onBackPressed() {
        Intent homeActivity = new Intent(context, HomeActivity.class);
        //Log.d("INTENT","After Intent creation");  //Debug
        startActivity(homeActivity);
        overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
    }

    private void initializeCardView(){
        moodRecyclerView = findViewById(R.id.mood_recycleView);
        moodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moodCardList = new ArrayList<MoodCard>();

        cardAdapter = new CardAdapter(this, moodCardList, new CardAdapter.ItemCardClickListener() {
            @Override
            public void onItemClick(MoodCard moodCard) {
                //showToast(moodCard.getMoodTitle()+ " : Clicked!"); //DEBUG LINE
                Intent intent = new Intent(getApplicationContext(), MoodActivity.class); //CardsViewActivity.this
                intent.putExtra("MoodImageID", moodCard.getMoodImage()); //int
                intent.putExtra("MoodTitle", moodCard.getMoodTitle()); //String
                intent.putExtra("MoodName", moodCard.getMoodDescription()); //String
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
            }
        }); //INSERT THE 2nd PARAMETER
        moodRecyclerView.setAdapter(cardAdapter);

        createDataForCards();
    }

    //method to test clicks on cards (RecycleView)
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void createDataForCards(){
        MoodCard moodCard;

        moodCard = new MoodCard(R.mipmap.image_joy_green, "JOY", "Gioioso");
        moodCardList.add(moodCard);

        moodCard = new MoodCard(R.mipmap.image_sorrow_green, "SORROW", "Triste");
        moodCardList.add(moodCard);

        moodCard = new MoodCard(R.mipmap.image_anger_green, "ANGER", "Arrabbiato");
        moodCardList.add(moodCard);

        moodCard = new MoodCard(R.mipmap.image_disgust_green, "DISGUST", "Disgustato");
        moodCardList.add(moodCard);

        moodCard = new MoodCard(R.mipmap.image_fear_green, "FEAR", "Impaurito");
        moodCardList.add(moodCard);

        moodCard = new MoodCard(R.mipmap.image_hungry_green, "HUNGRY", "Affamato");
        moodCardList.add(moodCard);

        cardAdapter.notifyDataSetChanged();
    }
}