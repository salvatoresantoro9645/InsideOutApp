package com.example.insideout;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

public class NoteCardAdapter extends RecyclerView.Adapter<NoteCardAdapter.NoteCardsHolder>{

    //private Context context; //not recommended
    private List<NoteCard> noteCardsList;

    public NoteCardAdapter(Context context, List<NoteCard> noteCardsList){
        //this.context = context; //not recommended
        this.noteCardsList = noteCardsList;
    }

    @NonNull
    @Override
    public NoteCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteCardsHolder cardHolder = null;
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.note_cardview, parent, false);
        cardHolder = new NoteCardsHolder(view);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteCardsHolder holder, int position) {
        NoteCard noteCard = noteCardsList.get(position);
        holder.setCardDetails(noteCard);

        boolean isVisible = noteCard.visibility;
        holder.expandedText.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        holder.noteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteCardsList.get(holder.getAdapterPosition()).isVisibility()) {
                    noteCardsList.get(holder.getAdapterPosition()).visibility = false;
                    ((MaterialCardView)view).findViewById(R.id.textNoteText_CardView).setVisibility(View.GONE);
                }
                else{
                    ((MaterialCardView)view).findViewById(R.id.textNoteText_CardView).setVisibility(View.VISIBLE);
                    noteCardsList.get(holder.getAdapterPosition()).visibility = true;
                }

                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteCardsList.size();
    }

    public static class NoteCardsHolder extends RecyclerView.ViewHolder{  //inserted static

        private MaterialCardView noteCardView;
        private TextView textNoteTitle; //Title == noteId + mood
        private TextView textNoteDateTime; //dd-MM-yyyy HH:mm:ss
        private TextView textNoteText; //to scroll-down
        private TextView expandedText; //ADDEDDDD NOWWWWWWWWW

        public NoteCardsHolder(@NonNull View itemView) {
            super(itemView);
            textNoteTitle = (TextView) itemView.findViewById(R.id.textNoteTitle_CardView);
            textNoteDateTime = (TextView) itemView.findViewById(R.id.textNoteDate_CardView);
            textNoteText = (TextView) itemView.findViewById(R.id.textNoteText_CardView);
            this.expandedText = (TextView) itemView.findViewById(R.id.textNoteText_CardView);  //ADDEDDDD NOWWWWWWWWW

            this.noteCardView = (MaterialCardView) itemView.findViewById(R.id.note_CardView);
        }

        //TO FIX WITH NOTE VALUEEE!!!!!
        public void setCardDetails(NoteCard noteCard){
            textNoteTitle.setText(noteCard.getNoteTitle());
            textNoteTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textNoteTitle.setSingleLine();
            textNoteTitle.setHorizontallyScrolling(true);
            textNoteTitle.setMarqueeRepeatLimit(-1);
            textNoteTitle.setMaxLines(1);
            textNoteTitle.setSelected(true);
            textNoteDateTime.setText(noteCard.getNoteDateTime());
            textNoteText.setText(noteCard.getNoteText());
        }
    }
}

