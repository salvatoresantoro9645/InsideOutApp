package com.example.insideout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SaveNoModifyNoteDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_nomodify_layout, null));

        //builder.setMessage(R.string.dialog_noModify_message)
        //        .setTitle(R.string.dialog_noModify_title)
        builder.setPositiveButton(R.string.dialog_noModify_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //User back to the previous activity without exit
                        //Handled in NoteActivity
                        listener.onDialogPositiveClick(SaveNoModifyNoteDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.dialog_noModify_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //User cancelled the dialog
                        //Handled in NoteActivity
                        listener.onDialogNegativeClick(SaveNoModifyNoteDialogFragment.this);

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeNoModifyDialogListener {
        public void onDialogPositiveClick(SaveNoModifyNoteDialogFragment dialog);
        public void onDialogNegativeClick(SaveNoModifyNoteDialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeNoModifyDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeNoModifyDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeNoModifyDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(NoteActivity.class
                    + " must implement NoticeNoModifyDialogListener");
        }
    }


}

