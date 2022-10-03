package com.example.insideout;

import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.EventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicActivity extends ActionBaseActivity{

    private static final String KEY_ITEM_INDEX = "item_index";
    private static final String KEY_POSITION = "position";
    private static final String KEY_AUTO_PLAY = "auto_play";
    private static final String KEY_LAST_POSITION = "last_position";
    private static final String KEY_LAST_ITEM_INDEX = "last_item_index";
    private static final String MOOD_TITLE = "MoodTitle";


    private PlayerView exoPlayerView;
    private @Nullable ExoPlayer exoPlayer;  //become null after releasing

    private List<MediaItem> mediaItems;
    private boolean startAutoPlay;
    private int startItemIndex;
    private long startPosition;
    private long lastPosition;
    private int lastItemIndex;

    private Context context;
    private RecyclerView musicRecyclerView;
    private MusicCardAdapter musicCardAdapter;
    private List<MusicCard> musicCardList;
    private String mood;  //value received in bundle to choose the playlist that should be create
    private MoodPlaylistCreator moodPlaylistCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_music); using abstract method getLayoutResourceId
        context = this;

        exoPlayerView = findViewById(R.id.playerView);
        exoPlayerView.requestFocus();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mood = bundle.getString(MOOD_TITLE);
        }

        if (savedInstanceState != null) {
            startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
            startItemIndex = savedInstanceState.getInt(KEY_ITEM_INDEX);
            startPosition = savedInstanceState.getLong(KEY_POSITION);
            lastPosition = savedInstanceState.getLong(KEY_LAST_POSITION);
            lastItemIndex = savedInstanceState.getInt(KEY_LAST_ITEM_INDEX);
            mood = savedInstanceState.getString(MOOD_TITLE);
        }


        moodPlaylistCreator = new MoodPlaylistCreator(mood);
        initializeCardView(); //initialize the cardView

        View rootView = musicRecyclerView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.my_green_200));
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_music;
    }

    private void initializeCardView(){
        musicRecyclerView = findViewById(R.id.music_recycleView);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        musicCardList = new ArrayList<MusicCard>();

        createDataForCards();
        musicCardAdapter = new MusicCardAdapter(this, musicCardList, new MusicCardAdapter.ItemCardClickListener() {
            @Override
            public void onItemClick(MusicCard musicCard) {
                //showToast(musicCard.getMusicTitle()+ " : Clicked!"); //DEBUG LINE
                //here we choose and play the song
                //and change the cardView text color (not done...)
                exoPlayer.seekTo(musicCardList.indexOf((MusicCard) musicCard), 0);
                exoPlayer.setPlayWhenReady(true);
            }
        });
        musicRecyclerView.setAdapter(musicCardAdapter);
    }

    //method to test clicks on cards (RecycleView)
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void createDataForCards(){
        musicCardList = moodPlaylistCreator.createCardList(this.musicCardList);
        //musicCardAdapter.notifyDataSetChanged(); //in case of dynamic change
    }

    @Override
    @OptIn(markerClass = UnstableApi.class)
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
            if (exoPlayerView != null) {
                exoPlayerView.onResume();
            }
        }
    }

    @Override
    @OptIn(markerClass = UnstableApi.class)
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || exoPlayer == null) {
            initializePlayer();
            if (exoPlayerView != null) {
                exoPlayerView.onResume();
            }
        }
        if(lastPosition!=0 && exoPlayer!=null){
            exoPlayer.seekTo(lastItemIndex, lastPosition);
            //Log.e("ON_RESUME", "seekTo_CurrentPosition"+exoPlayer.getCurrentPosition());  //Debug
        }
        //Log.e("ON_RESUME", "I'm in onResume");  //Debug
    }

    @Override
    @OptIn(markerClass = UnstableApi.class)
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (exoPlayerView != null) {
                exoPlayerView.onPause();
                lastPosition = exoPlayer.getCurrentPosition(); //ADDEDDDD
                lastItemIndex = exoPlayer.getCurrentMediaItemIndex();
                //Log.e("ON_PAUSE", "LastPosition"+lastPosition);
            }
            releasePlayer();
        }
        //Log.e("ON_PAUSE", "I'm in onPause");  //Debug
    }

    @Override
    @OptIn(markerClass = UnstableApi.class)
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (exoPlayerView != null) {
                exoPlayerView.onPause();
                lastPosition = exoPlayer.getCurrentPosition();
                lastItemIndex = exoPlayer.getCurrentMediaItemIndex();
                //Log.e("ON_STOP", "LastPosition "+lastPosition);
            }
            releasePlayer();
        }
        //Log.e("ON_STOP", "I'm in onStop");  //Debug
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        updateStartPosition();
        outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
        outState.putInt(KEY_ITEM_INDEX, startItemIndex);
        outState.putLong(KEY_POSITION, startPosition);
        outState.putLong(KEY_LAST_POSITION, lastPosition);
        outState.putInt(KEY_LAST_ITEM_INDEX, lastItemIndex);
        outState.putString(MOOD_TITLE, this.mood);
        //Log.e("OUTSTATE", "STORING MOOD "+this.mood);  //Debug
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
        startItemIndex = savedInstanceState.getInt(KEY_ITEM_INDEX);
        startPosition = savedInstanceState.getLong(KEY_POSITION);
        lastPosition = savedInstanceState.getLong(KEY_LAST_POSITION);
        lastItemIndex = savedInstanceState.getInt(KEY_LAST_ITEM_INDEX);
        mood = savedInstanceState.getString(MOOD_TITLE);
        //Log.e("RESUMEDSTATE", "RESUMED MOOD "+this.mood); //Debug
    }


    @OptIn(markerClass = UnstableApi.class)
    protected boolean initializePlayer() {
        if (exoPlayer == null) {
            createMediaItems();
            //Log.e("INITIALIZE_PLAYER", "EXOPLAYER == null");  //Debug
            if (mediaItems.isEmpty()) {
                return false;
            }

            exoPlayer = new ExoPlayer.Builder(this).build();
            exoPlayer.setPlayWhenReady(startAutoPlay);

            exoPlayerView.setPlayer(exoPlayer);
        }
        boolean haveStartPosition = startItemIndex != C.INDEX_UNSET;
        if (haveStartPosition) {
            exoPlayer.seekTo(startItemIndex, startPosition);
            //Log.e("SEEK_TO", ""+startPosition); //Debug
        }

        exoPlayer.setMediaItems(mediaItems, /* resetPosition= */ !haveStartPosition);
        exoPlayer.addListener(new Player.Listener(){
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED ||
                        !playWhenReady) {

                    exoPlayerView.setKeepScreenOn(false);
                } else { // STATE_READY, STATE_BUFFERING
                    // This prevents the screen from getting dim/lock
                    exoPlayerView.setKeepScreenOn(true);
                }
            }
        });
        exoPlayer.prepare();

        //updateButtonVisibility();
        return true;
    }

    @OptIn(markerClass = UnstableApi.class)
    protected void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
            exoPlayerView.setPlayer(null);
            mediaItems = Collections.emptyList();
        }
    }


    private void updateStartPosition() {
        if (exoPlayer != null) {
            startAutoPlay = exoPlayer.getPlayWhenReady();
            startItemIndex = exoPlayer.getCurrentMediaItemIndex();
            startPosition = Math.max(0, exoPlayer.getContentPosition());
            //Log.e("UPDATE_START_POSITION", "currentPosition "+startPosition);
        }
    }

    //set mediaItems list using uri to play mp3 files from firebase storage
    @OptIn(markerClass = UnstableApi.class)
    private void createMediaItems(){
        mediaItems = moodPlaylistCreator.createPlaylist();
    }

    /* //Unused
    protected void clearStartPosition() {
        startAutoPlay = true;
        startItemIndex = C.INDEX_UNSET;
        startPosition = C.TIME_UNSET;
    }
     */

}