package com.example.insideout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PERSONGIVENNAME = "personGivenName";

    // TODO: Rename and change types of parameters
    private static String personGivenName;
    private TextView textView_helloUser;
    private CardView cardView1;
    private CardView cardView2;
    private ImageView image_cardView1;
    private ImageView image_cardView2;
    private Button button_cardView1;
    private Button button_cardView2;

    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PERSONGIVENNAME, personGivenName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personGivenName = getArguments().getString(ARG_PERSONGIVENNAME);
        }
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.setBackgroundColor(getResources().getColor(R.color.my_green_200));
        textView_helloUser = view.findViewById(R.id.hello_user_text);
        cardView1 = view.findViewById(R.id.manual_mood_selection_cardview);
        cardView2 = view.findViewById(R.id.ml_face_detection_cardview);
        image_cardView1 = view.findViewById(R.id.cardview1_image);
        image_cardView2 = view.findViewById(R.id.cardview2_image);
        textView_helloUser.setText("Hello " + personGivenName);
        //textView_helloUser.setTextAppearance(R.style.);  //STYLE TO FIX!!!!!!

        //Resize and fit second image on CardView's ImageView
        Picasso.with(context)
                .load(R.drawable.image_ml_face_detection)
                .fit()
                .into(image_cardView2);

        button_cardView1 = view.findViewById(R.id.cardview1_button);
        button_cardView2 = view.findViewById(R.id.cardview2_button);

        button_cardView1.setOnClickListener(this);
        button_cardView2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cardview1_button:
                Intent intent = new Intent(context, MoodSelectionActivity.class);
                //Log.d("INTENT","After Intent creation");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_medium, R.anim.fade_out_medium);
                break;
            case R.id.cardview2_button:
                showToast(getResources().getString(R.string.ml_new_feature));
                break;
            default:
                //Do Nothing
        }
    }

    //method to show message after click on card "ML_Face_Detection"
    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}