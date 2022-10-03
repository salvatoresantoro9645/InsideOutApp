package com.example.insideout;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardsHolder>{

    //private Context context; //not recommended
    private List<MoodCard> moodCardsList;
    private ItemCardClickListener itemCardListener;

    public CardAdapter(Context context, List<MoodCard> moodCardsList, ItemCardClickListener itemCardListener){
        //this.context = context;  //not recommended
        this.moodCardsList = moodCardsList;
        this.itemCardListener = itemCardListener;
    }

    //TO IMPLEMENT!!!
    @NonNull
    @Override
    public CardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new CardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsHolder holder, int position) {
        MoodCard moodCard = moodCardsList.get(position);
        holder.setCardDetails(moodCard);

        holder.itemView.setOnClickListener(view -> {
            itemCardListener.onItemClick(moodCardsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return moodCardsList.size();
    }

    public interface ItemCardClickListener{
        void onItemClick(MoodCard moodCard);
    }

    class CardsHolder extends RecyclerView.ViewHolder{

        private ImageView imageAuthorImage;
        private TextView textSongTitle;
        private TextView textAuthorName;

        public CardsHolder(@NonNull View itemView) {
            super(itemView);
            imageAuthorImage = (ImageView) itemView.findViewById(R.id.imageMood_CardView);
            textSongTitle = (TextView) itemView.findViewById(R.id.textMoodTitle_CardView);
            textAuthorName = (TextView) itemView.findViewById(R.id.textMoodDescription_CardView);
        }

        public void setCardDetails(MoodCard moodCard){
            //imageAuthorImage.setImageDrawable(moodCard.getAuthorImage());
            imageAuthorImage.setImageResource(moodCard.getMoodImage());
            textSongTitle.setText(moodCard.getMoodTitle());
            textSongTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textSongTitle.setSingleLine();
            textSongTitle.setHorizontallyScrolling(true);
            textSongTitle.setMarqueeRepeatLimit(-1);
            textSongTitle.setMaxLines(1);
            textSongTitle.setSelected(true);
            textAuthorName.setText(moodCard.getMoodDescription());
        }
    }
}
