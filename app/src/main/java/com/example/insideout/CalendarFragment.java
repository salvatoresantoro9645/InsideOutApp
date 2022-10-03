package com.example.insideout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment implements View.OnClickListener, DatePicker.OnDateChangedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatePicker calendarView;
    private MaterialButton showNoteButton;
    //private TextView textDatabase;  //For Database Debugging
    private Date currentDate; //NO NEEDEDDDD...
    private int selectedYear, selectedMonth, selectedDay;
    private Context context;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        view.setBackgroundColor(getResources().getColor(R.color.calendar_fragment_background));
        this.calendarView = (DatePicker) view.findViewById(R.id.calendar);
        String strCurrentDate = setCurrentDate();
        this.showNoteButton = (MaterialButton) view.findViewById(R.id.showNoteButton);
        showNoteButton.setOnClickListener(this);
        //this.textDatabase = (TextView) view.findViewById(R.id.textDatabase);  //For Database Debugging
        //textDatabase.setText(strCurrentDate);  //For Database Debugging
        return view;
    }

    public String setCurrentDate(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
        selectedYear = calendarView.getYear();
        selectedMonth = calendarView.getMonth();
        selectedDay = calendarView.getDayOfMonth();
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(selectedYear, selectedMonth, selectedDay);
        currentDate = calendarDate.getTime();
        calendarView.init(selectedYear, selectedMonth, selectedDay, this);
        String strDate = sdfDate.format(currentDate);
        return strDate;
    }


    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        this.selectedYear = year;
        this.selectedMonth = month;
        this.selectedDay = day;

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(year, month, day);
        currentDate = calendarDate.getTime();
        calendarView.init(year, month, day, this);
        //String strDate = sdfDate.format(currentDate);
        //textDatabase.setText(strDate);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showNoteButton:
                //TO CONTINUEEEE
                ShowNoteFragment showNoteFragment = new ShowNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("SelectedYear", selectedYear);
                bundle.putInt("SelectedMonth", selectedMonth);
                bundle.putInt("SelectedDay", selectedDay);
                showNoteFragment.setArguments(bundle);
                //Log.e("SELECTED_DATES", selectedDay+"-"+selectedMonth+"-"+selectedYear);
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.dialog_enter, R.anim.dialog_exit);
                ft.replace(R.id.layout_calendar_fragment, showNoteFragment);
                ft.commit();
                break;
            default:
                //no operation to perform
        }
    }
}