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

public class ActionCardAdapter extends RecyclerView.Adapter<ActionCardAdapter.ActionCardsHolder>{

    private static final String MOOD_TITLE = "MoodTitle";

    //private Context context;  //not recommended
    private List<ActionCard> actionCardsList;
    private String mood;

    public ActionCardAdapter(Context context, List<ActionCard> actionCardsList, String mood){
        //this.context = context;  //not recommended
        this.actionCardsList = actionCardsList;
        this.mood = mood;
    }

    //TO IMPLEMENT!!!
    @NonNull
    @Override
    public ActionCardAdapter.ActionCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_cardview, parent, false);
        return new ActionCardAdapter.ActionCardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionCardAdapter.ActionCardsHolder holder, int position) {
        ActionCard actionCard = actionCardsList.get(position);
        holder.setCardDetails(actionCard);
        String mood = this.mood;

        String actionCardTitle = actionCard.getActionTitle();
        Context context = holder.actionButton.getContext();
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(actionCardTitle){
                    case "MUSIC":
                        Intent musicIntent = new Intent(context, MusicActivity.class);
                        musicIntent.putExtra(MOOD_TITLE, mood);
                        Log.d("INTENT","After Intent creation");
                        context.startActivity(musicIntent);
                        break;
                    case "FILM":
                        Intent filmIntent = new Intent(context, FilmActivity.class);
                        filmIntent.putExtra(MOOD_TITLE, mood);
                        Log.d("INTENT","After Intent creation");
                        context.startActivity(filmIntent);
                        break;
                    case "GPS":
                        Intent gpsIntent = new Intent(context, GPSActivity.class);
                        gpsIntent.putExtra(MOOD_TITLE, mood);
                        Log.d("INTENT","After Intent creation");
                        context.startActivity(gpsIntent);
                        break;
                    case "NOTE":
                        Intent NoteIntent = new Intent(context, NoteActivity.class);
                        NoteIntent.putExtra(MOOD_TITLE, mood);
                        Log.d("INTENT","After Intent creation");
                        context.startActivity(NoteIntent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionCardsList.size();
    }

    class ActionCardsHolder extends RecyclerView.ViewHolder{

        private ImageView imageActionImage;
        private TextView textActionTitle;
        private TextView textActionDescription;
        private MaterialButton actionButton;

        public ActionCardsHolder(@NonNull View itemView) {
            super(itemView);
            imageActionImage = (ImageView) itemView.findViewById(R.id.imageAction_CardView);
            textActionTitle = (TextView) itemView.findViewById(R.id.textActionTitle_CardView);
            textActionDescription = (TextView) itemView.findViewById(R.id.textActionDescription_CardView);
            actionButton = (MaterialButton) itemView.findViewById((R.id.startAction_button));
        }

        public void setCardDetails(ActionCard actionCard){
            //imageAuthorImage.setImageDrawable(moodCard.getAuthorImage());
            imageActionImage.setImageResource(actionCard.getActionImage());
            textActionTitle.setText(actionCard.getActionTitle());
            textActionTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textActionTitle.setSingleLine();
            textActionTitle.setHorizontallyScrolling(true);
            textActionTitle.setMarqueeRepeatLimit(-1);
            textActionTitle.setMaxLines(1);
            textActionTitle.setSelected(true);
            textActionDescription.setText(actionCard.getActionDescription());
        }
    }
}
