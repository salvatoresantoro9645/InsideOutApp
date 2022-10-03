package com.example.insideout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public abstract class ActionBaseActivity extends AppCompatActivity {

    private static final String MOOD_TITLE = "MoodTitle";

    private Toolbar titlebar;
    private Context context;
    private String mood = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.mood = bundle.getString(MOOD_TITLE);
            //Log.e("MOOD_TITLE", this.mood);  //Debug
        }
        setContentView(getLayoutResourceId());

        setTitlebarBackButton();
    }

    protected abstract int getLayoutResourceId();

    protected void setTitlebarBackButton(){
        titlebar = (Toolbar) findViewById(R.id.titleToolbar);
        setSupportActionBar(titlebar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_36);
        titlebar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MoodSelectionActivity.super.onBackPressed();
                Intent intent = new Intent(context, MoodActivity.class);
                //Log.d("INTENT","After Intent creation");  //Debug
                intent.putExtra(MOOD_TITLE, mood);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MoodActivity.class);
        //Log.d("INTENT","After Intent creation");  //Debug
        intent.putExtra(MOOD_TITLE, mood);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
    }
}