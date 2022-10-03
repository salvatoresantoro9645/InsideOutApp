package com.example.insideout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PERSONNAME = "personName";
    private static final String ARG_PERSONGIVENNAME = "personGivenName";
    private static final String ARG_PERSONFAMILYNAME = "personFamilyName";
    private static final String ARG_PERSONEMAIL = "personEmail";
    private static final String ARG_PERSONID = "personId";
    private static final String ARG_PERSONPHOTO = "personPhoto";

    private static String personName;
    private static String personGivenName;
    private static String personFamilyName;
    private static String personEmail;
    private static String personId;
    private static Uri personPhoto;

    private TextView personNameView;
    private TextView personGivenNameView;
    private TextView personFamilyNameView;
    private TextView personEmailView;
    private ImageView personPhotoView;
    //private TextView personIdView;

    private Button signOutButton;
    private GoogleSignInClient mGoogleSignInClient;
    private Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();

        bundle.putString(ARG_PERSONNAME, personName);
        bundle.putString(ARG_PERSONGIVENNAME, personGivenName);
        bundle.putString(ARG_PERSONFAMILYNAME, personFamilyName);
        bundle.putString(ARG_PERSONEMAIL, personEmail);
        bundle.putString(ARG_PERSONID, personId);
        bundle.putParcelable(ARG_PERSONPHOTO, personPhoto);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.personName = getArguments().getString(ARG_PERSONNAME);
            this.personGivenName = getArguments().getString(ARG_PERSONGIVENNAME);;
            this.personFamilyName = getArguments().getString(ARG_PERSONFAMILYNAME);;
            this.personEmail = getArguments().getString(ARG_PERSONEMAIL);;
            this.personId = getArguments().getString(ARG_PERSONID);;
            this.personPhoto = getArguments().getParcelable(ARG_PERSONPHOTO);
        }
        context = this.getContext();

        // Configure sign-in to request the user's ID, email address, and basic profile
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //view.setBackgroundColor(getResources().getColor(R.color.profile_color));  //layout fixed in xml

        this.personNameView = view.findViewById(R.id.personName);
        this.personGivenNameView = view.findViewById(R.id.personGivenName);
        this.personFamilyNameView = view.findViewById(R.id.personFamilyName);
        this.personEmailView = view.findViewById(R.id.personEmail);
        this.personPhotoView = view.findViewById(R.id.personPhoto);
        //this.personIdView = view.findViewById(R.id.personId);

        setUserProfileInformations();

        //Prepare sign-out Button
        signOutButton = (Button) view.findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);

        //BUTTON WITH ON CLICK LISTENER TO SET!!!
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signOutButton:
                signOut();
                break;
            default:
                //no operation to perform
        }
    }

    private void signOut() {
        if(mGoogleSignInClient != null) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }
                    });
        }
    }

    private void setUserProfileInformations(){
        if(personPhoto == null){
            //Log.d("NULL_IMAGE","personPhoto is a Null Image");
            personPhotoView.setImageResource(R.drawable.baseline_account_circle_18);
        }
        else {
            //Log.d("NO_NULL_IMAGE",personPhoto.toString());
            Picasso.with(context).load(personPhoto).into(personPhotoView);
        }

        String prefixStr = "";
        if(personName != null) {
            this.personNameView.setText(personName);
        }
        if(personEmail != null) {
            this.personEmailView.setText("Email\n" + personEmail);
            textFormat(personEmailView);
            this.personEmailView.setEllipsize(TextUtils.TruncateAt.END);
        }
        if(personGivenName != null) {
            this.personGivenNameView.setText("Name\n" + personGivenName);
            //this.personGivenNameView.setText("Name: " + personGivenName);
            textFormat(personGivenNameView);
        }
        if(personFamilyName != null) {
            this.personFamilyNameView.setText("Surname\n" + personFamilyName);
            textFormat(personFamilyNameView);
        }
        /*Text that show UserId (useful for profile fragment)
        if(personIdView != null) {
            this.personIdView.setText("UserId\n" + personId);
            textFormat(personIdView);
            this.personIdView.setEllipsize(TextUtils.TruncateAt.END);
        }
        */
    }

    //method to split string in bold (until ':') and no bold (after ':')
    public void textFormat(final TextView view) {
        final CharSequence text = view.getText();
        if (!TextUtils.isEmpty(text)) {
            final int index = text.toString().indexOf('\n');
            if (index > 0) {
                final SpannableStringBuilder sb = new SpannableStringBuilder(text);
                sb.setSpan(new RelativeSizeSpan(0.8f), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); // set size
                sb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.my_azure_100)), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); //set color
                sb.setSpan(new StyleSpan(Typeface.BOLD), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); //set style
                view.setText(new SpannableString(sb));
            }
        }
    }
}