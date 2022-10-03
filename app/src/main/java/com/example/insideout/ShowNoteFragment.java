package com.example.insideout;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowNoteFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "SelectedYear";
    private static final String ARG_PARAM2 = "SelectedMonth";
    private static final String ARG_PARAM3 = "SelectedDay";

    private RecyclerView noteRecyclerView;
    private NoteCardAdapter noteCardAdapter;
    private List<NoteCard> noteCardList;
    private MaterialButton exitButton;
    private Context context;

    private int selectedYear, selectedMonth, selectedDay;

    public ShowNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowNoteFragment newInstance(int param1, int param2, int param3) {
        ShowNoteFragment fragment = new ShowNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedYear = getArguments().getInt(ARG_PARAM1);
            selectedMonth = getArguments().getInt(ARG_PARAM2);
            selectedDay = getArguments().getInt(ARG_PARAM3);
            //Log.d("ON_CREATE_SHOW_NOTE_FRAGMENT", selectedDay+"-"+selectedMonth+"-"+selectedYear);
        }
        context = getContext();
        noteCardList = new ArrayList<NoteCard>();
        createDataForCards();
        this.noteCardAdapter = new NoteCardAdapter(this.context, noteCardList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            selectedYear = getArguments().getInt(ARG_PARAM1);
            selectedMonth = getArguments().getInt(ARG_PARAM2);
            selectedDay = getArguments().getInt(ARG_PARAM3);
            //Log.d("ON_CREATE_VIEW_SHOW_NOTE_FRAGMENT", selectedDay+"-"+selectedMonth+"-"+selectedYear);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_note, container, false);
        this.noteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycleView);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        noteRecyclerView.setAdapter(noteCardAdapter);
        this.exitButton = (MaterialButton) view.findViewById(R.id.exitNoteButton);
        exitButton.setOnClickListener(this);
        if(noteCardList.isEmpty()){
            insertNoNoteTextView(view);
            //textView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT));
        }
        return view;
    }

    public void insertNoNoteTextView(View view){
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.noteShow_FragmentContainer);
        ConstraintSet set = new ConstraintSet();
        TextView textView = new TextView(context);

        textView.setId(View.generateViewId());
        textView.setText("No note written in this day");
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.my_azure_100));
        constraintLayout.addView(textView);
        set.clone(constraintLayout);
        set.connect(textView.getId(), ConstraintSet.TOP, R.id.noteShow_FragmentContainer, ConstraintSet.TOP, 0);
        set.connect(textView.getId(), ConstraintSet.BOTTOM, R.id.noteShow_FragmentContainer, ConstraintSet.BOTTOM, 0);
        set.connect(textView.getId(), ConstraintSet.RIGHT, R.id.noteShow_FragmentContainer, ConstraintSet.RIGHT, 0);
        set.connect(textView.getId(), ConstraintSet.LEFT, R.id.noteShow_FragmentContainer, ConstraintSet.LEFT, 0);
        set.applyTo(constraintLayout);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.exitNoteButton:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.dialog_enter, R.anim.dialog_exit);
                ft.remove(this);
                ft.commit();
                break;
            default:
                //Do nothing
        }
    }

    private void createDataForCards(){
        NoteCard noteCard;
        String noteTitle; //Title == noteId + mood
        String noteDateTime;
        String noteText;
        String noteAuthorId = "";

        GoogleSignInAccount actualUser = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(actualUser != null){
            noteAuthorId = actualUser.getId();
        }

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(this.selectedYear, this.selectedMonth, this.selectedDay);
        Date reqDate = calendarDate.getTime();
        String strReqDate = sdfDate.format(reqDate);
        //Log.d("Create_Data_For_Card_2222", strReqDate);

        AppDatabase db = AppDatabase.getDbInstance(context);
        //Note[] notes = db.noteDao().loadAllNotes();
        Note[] notes = db.noteDao().loadAllNotesForReqDate(noteAuthorId, strReqDate);
        for(int i=0; i<notes.length; i++){
            //noteTitle = notes[i].getNoteId()+" "+notes[i].getMood(); //Debug
            noteTitle = ""+notes[i].getMood();
            noteDateTime = notes[i].getDateTime();
            noteText = notes[i].getText();
            noteCard = new NoteCard(noteTitle, noteDateTime, noteText);
            noteCardList.add(noteCard);
        }
        //noteCardAdapter.notifyDataSetChanged();  //in case of dynamic change
    }

}