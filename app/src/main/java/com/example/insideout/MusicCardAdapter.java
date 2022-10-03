package com.example.insideout;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MusicCardAdapter extends RecyclerView.Adapter<MusicCardAdapter.MusicCardsHolder>{

    //private Context context;  //not recommended
    private List<MusicCard> musicCardsList;
    private MusicCardAdapter.ItemCardClickListener itemCardListener;
    private int selectedItem; //Used for "white_color" feature

    public MusicCardAdapter(Context context, List<MusicCard> musicCardsList, MusicCardAdapter.ItemCardClickListener itemCardListener){ //String Mood passed
        //this.context = context;  //not recommended
        this.musicCardsList = musicCardsList;
        this.itemCardListener = itemCardListener;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public MusicCardAdapter.MusicCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_cardview, parent, false);
        return new MusicCardAdapter.MusicCardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicCardAdapter.MusicCardsHolder holder, int position) {
        MusicCard musicCard = musicCardsList.get(position);
        holder.setCardDetails(musicCard);

        holder.itemView.setOnClickListener(view -> {
            //holder.textMusicTitle.setTextColor(context.getResources().getColor(R.color.white));
            selectedItem = holder.getAdapterPosition(); //Used for "white_color" feature
            itemCardListener.onItemClick(musicCardsList.get(position));
            notifyDataSetChanged(); //ADDED
        });

        //Disabled because after next or previous click of exoplayer
        //I can't change color of CardView (WHITE_COLOR FEATURE) (Player.Listener...)
        /*
        if(selectedItem==position){
            holder.textMusicTitle.setTextColor(context.getResources().getColor(R.color.white));
        }
        else{
            holder.textMusicTitle.setTextColor(context.getResources().getColor(R.color.black));
        }
         */
    }

    public void setSelectedItem(int position){
        selectedItem = position;
    }

    @Override
    public int getItemCount() {
        return musicCardsList.size();
    }

    public interface ItemCardClickListener{
        void onItemClick(MusicCard musicCard);
    }

    class MusicCardsHolder extends RecyclerView.ViewHolder{

        private ImageView imageMusicImage;
        private TextView textMusicTitle;
        private TextView textMusicAuthor;

        public MusicCardsHolder(@NonNull View itemView) {
            super(itemView);
            imageMusicImage = (ImageView) itemView.findViewById(R.id.imageMusic_CardView);
            textMusicTitle = (TextView) itemView.findViewById(R.id.textMusicTitle_CardView);
            textMusicAuthor = (TextView) itemView.findViewById(R.id.textMusicAuthor_CardView);
        }

        public void setCardDetails(MusicCard musicCard){
            imageMusicImage.setImageResource(musicCard.getMusicImage());
            textMusicTitle.setText(musicCard.getMusicTitle());
            textMusicAuthor.setText(musicCard.getMusicAuthor());
        }
    }
}
