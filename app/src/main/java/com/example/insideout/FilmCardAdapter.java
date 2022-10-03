package com.example.insideout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmCardAdapter extends RecyclerView.Adapter<FilmCardAdapter.FilmCardsHolder>{

    //private Context context;  //not recommended
    private List<FilmCard> filmCardsList;

    public FilmCardAdapter(Context context, List<FilmCard> filmCardsList){ //String Mood passed
        //this.context = context;  //not recommended
        this.filmCardsList = filmCardsList;
    }

    @NonNull
    @Override
    public FilmCardAdapter.FilmCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_cardview, parent, false);
        return new FilmCardAdapter.FilmCardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmCardAdapter.FilmCardsHolder holder, int position) {
        FilmCard filmCard = filmCardsList.get(position);
        holder.setCardDetails(filmCard);
        holder.buttonFilmWatchTrailer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(view == holder.buttonFilmWatchTrailer){
                    Context context = holder.buttonFilmWatchTrailer.getContext();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(filmCardsList.get(holder.getAdapterPosition()).getFilmTrailerLink()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmCardsList.size();
    }


    class FilmCardsHolder extends RecyclerView.ViewHolder{

        private ImageView imageFilmImage;
        private TextView textFilmTitle;
        private TextView textFilmGenre;
        private Button buttonFilmWatchTrailer;

        public FilmCardsHolder(@NonNull View itemView) {
            super(itemView);
            imageFilmImage = (ImageView) itemView.findViewById(R.id.imageFilm_CardView);
            textFilmTitle = (TextView) itemView.findViewById(R.id.textFilmTitle_CardView);
            textFilmGenre = (TextView) itemView.findViewById(R.id.textFilmGenre_CardView);
            buttonFilmWatchTrailer = (MaterialButton) itemView.findViewById(R.id.watch_trailer_button);
        }

        public void setCardDetails(FilmCard filmCard){
            imageFilmImage.setImageResource(filmCard.getFilmImage());
            textFilmTitle.setText(filmCard.getFilmTitle());
            textFilmGenre.setText(filmCard.getFilmGenre());
        }
    }
}
