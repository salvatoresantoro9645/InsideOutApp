package com.example.insideout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteActivity extends ActionBaseActivity implements View.OnClickListener,
        SaveNoteDialogFragment.NoticeDialogListener, SaveNoModifyNoteDialogFragment.NoticeNoModifyDialogListener{

    private static final String MOOD_TITLE = "MoodTitle";

    private TextInputEditText editTextNoteView;
    private MaterialButton saveNoteButton;
    //private MaterialButton deleteNoteButton;  //For DataBase Debugging
    private Toolbar titlebar;
    private String mood;
    private Context context;
    private int enabledButton; //0 -> empty, -1 -> too much, 1 -> ok

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_music); using abstract method getLayoutResourceId
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mood = bundle.getString(MOOD_TITLE);
            //The key argument here must match that used in the other activity
        }
        context = this;
        enabledButton = 0;

        this.editTextNoteView = (TextInputEditText) findViewById(R.id.note_editText);
        editTextNoteView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //Do Nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //Do Nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().length() <= 0)) {
                    enabledButton = 0;
                }
                if((s.toString().length() > 625)){
                    enabledButton = -1;
                }
                if ((s.toString().length() > 0) && (s.toString().length() <= 625)) {
                    enabledButton = 1;
                }
                // this will show characters remaining
                //countTextView.setText(150 - s.toString().length() + "/150");
            }
        });
        setupUI(findViewById(R.id.note_parent_layout));
        this.saveNoteButton = (MaterialButton) findViewById(R.id.saveNoteButton);
        saveNoteButton.setOnClickListener(this);

        /*  //For DataBase Debugging
        //Button for Database Test (DeleteNote)
        this.deleteNoteButton = (MaterialButton) findViewById(R.id.deleteNoteButton);
        deleteNoteButton.setOnClickListener(this);
         */
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_note;
    }


    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof TextInputEditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(NoteActivity.this);
                    return false;
                }
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.saveNoteButton:
                switch(enabledButton){
                    case 0:
                        CharSequence textEmpty = "Write something before..."; //to fix in case of upperbound
                        Toast toastA = Toast.makeText(context, textEmpty, Toast.LENGTH_SHORT);
                        toastA.show();
                        break;
                    case -1:
                        CharSequence textTooMuch = "Too much characters..."; //to fix in case of upperbound
                        Toast toastB = Toast.makeText(context, textTooMuch, Toast.LENGTH_SHORT);
                        toastB.show();
                        break;
                    default:
                        //Do Save with Database
                        //safeNoteOnDatabase and return to previous activity
                        //saveNoteAndExit();
                        showNoticeNoModifyDialog();
                }
                break;
                /*  //For DataBase Debugging
            case R.id.deleteNoteButton:{
                AppDatabase db = AppDatabase.getDbInstance(context);
                Note[] notes = db.noteDao().loadAllNotes();
                if(notes != null) {
                    Toast toastC = Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT);
                    toastC.show();
                    db.noteDao().deleteNotes(notes);
                }
            }
                //safeNoteOnDatabase and return to previous activity
                break;
                 */
            default:
                break;
        }
    }

    private void saveNoteAndExit(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy"); //need only date without hour
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //need only date without hour
        Calendar calendarDate = Calendar.getInstance();
        Date currentDate = calendarDate.getTime();
        String strDate = sdfDate.format(currentDate);
        String strDateTime = sdfDateTime.format(currentDate);

        AppDatabase db = AppDatabase.getDbInstance(context);
        Note note = new Note();
        note.setText(editTextNoteView.getText().toString());
        note.setDate(strDate); //before was date
        //Log.d("strDate", strDate); //Debug
        note.setDateTime(strDateTime);
        //To get from previous Activity!!!!!!!! Extra of the Intent!!!
        GoogleSignInAccount actualUser = GoogleSignIn.getLastSignedInAccount(this);
        if (actualUser != null) {
            note.setAuthorId(actualUser.getId()); //use google sign-in user id!!!
            note.setAuthorName(actualUser.getGivenName()); //use google sign-in user givenName!!!
        }
        else{
            note.setAuthorId("404"); //use google sign-in user id!!!
            note.setAuthorName("Salvatore"); //use google sign-in user givenName!!!
        }
        note.setMood(mood); //use the value of Mood string passed as parameter of this activity in Intent!!!

        db.noteDao().insertNotes(note);

        //Toast //FIX WITH "NOTE SAVEDDDD"!!!!!!!!!!!!!
        Toast toast = Toast.makeText(context, "NOTE SAVED!", Toast.LENGTH_SHORT);
        toast.show();
        startPreviousActivity();
    }

    @Override
    protected void setTitlebarBackButton(){
        titlebar = (Toolbar) findViewById(R.id.titleToolbar);
        setSupportActionBar(titlebar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_36);
        titlebar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MoodSelectionActivity.super.onBackPressed();
                if(enabledButton!=0) {
                    showNoticeDialog();
                }
                else{
                    startPreviousActivity();
                }
            }
        });
    }

    //Use of SaveNoteDialogFragment
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        SaveNoteDialogFragment dialog = new SaveNoteDialogFragment();
        dialog.show(getSupportFragmentManager(), "SaveNoteDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(SaveNoteDialogFragment dialog) {
        // User touched the dialog's positive button
        startPreviousActivity();
    }

    @Override
    public void onDialogNegativeClick(SaveNoteDialogFragment dialog) {
        // User touched the dialog's negative button
        //Do Nothing... continue modifying note
    }

    @Override
    public void onDialogNeutralClick(SaveNoteDialogFragment dialog) {
        // User touched the dialog's "Save and exit" button
        saveNoteAndExit();
    }


    //Use of SaveNoModifyNoteDialogFragment
    public void showNoticeNoModifyDialog() {
        // Create an instance of the dialog fragment and show it
        SaveNoModifyNoteDialogFragment dialog = new SaveNoModifyNoteDialogFragment();
        dialog.show(getSupportFragmentManager(), "SaveNoModifyNoteDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(SaveNoModifyNoteDialogFragment dialog) {
        // User touched the dialog's positive button
        saveNoteAndExit();
    }

    @Override
    public void onDialogNegativeClick(SaveNoModifyNoteDialogFragment dialog) {
        // User touched the dialog's negative button
        //Do Nothing... continue modifying note
    }

    public void startPreviousActivity(){
        Intent intent = new Intent(context, MoodActivity.class);
        //Log.d("INTENT","After Intent creation"); //Debug
        intent.putExtra(MOOD_TITLE, this.mood);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
    }


    @Override
    public void onBackPressed() {
        if(enabledButton!=0) {
            showNoticeDialog();
        }
        else{
            startPreviousActivity();
        }
    }
}