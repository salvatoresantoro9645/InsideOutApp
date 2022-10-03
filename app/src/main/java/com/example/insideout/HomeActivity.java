package com.example.insideout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private static final String SELECTED_ITEM = "arg_selected_item"; //to handle navigation bar buttons
    private static final String TAG = "HOME_ACTIVITY";

    private int mySelectedItem; //0 -> home, 1 -> calendar, 2 -> profile
    private BottomNavigationView navigationView;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount mGoogleSignInAccount;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prepareBottomNavigationView();

        // Configure sign-in to request the user's ID, email address, and basic profile
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        this.mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        this.mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mySelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navigationView.getMenu().findItem(mySelectedItem);
        } else
        {
            selectedItem = navigationView.getMenu().getItem(0);
        }

        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mySelectedItem);
        super.onSaveInstanceState(outState);
    }


    private void revokeAccess() {
        if(this.mGoogleSignInClient != null) {
            this.mGoogleSignInClient.revokeAccess()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // ... REMOVE THIS ACCOUNT FROM DATABASE
                        }
                    });
        }
    }

    //OnClick I should perform three (3) different Fragment!!!
    private void prepareBottomNavigationView(){
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigationView.setVisibility(View.VISIBLE);
        navigationView.setOnItemSelectedListener(
                item -> {
                    boolean found = selectFragment(item);
                    return found;
                }
        );

        navigationView.setOnItemReselectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.home:
                            //showToast("We are in Home yet");
                            //Toast.makeText(context, "We are in home yet", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.calendar:
                            //showToast("Signed in before!");
                            //Toast.makeText(context, "Calendar : Sign in before...", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.profile:
                            //showToast("Sign in before!");
                            //Toast.makeText(context, "Profile : Sign in before...", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                    }
                }
        );
    }

    private boolean selectFragment(MenuItem item){
        Fragment fragment = null;
        item.setCheckable(true);
        boolean found = false;
        switch (item.getItemId()) {
            case R.id.home:
                Bundle bundleHome = getPersonGivenNameBundle();
                HomeFragment hFragment = new HomeFragment();
                if(bundleHome != null){
                    hFragment.setArguments(bundleHome);
                }
                FragmentTransaction hFragmentTransaction = getSupportFragmentManager().beginTransaction();
                //hFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                hFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                hFragmentTransaction.replace(R.id.fragmentContainer, hFragment, "");
                hFragmentTransaction.addToBackStack(null); //ADDED!!!
                hFragmentTransaction.commit();
                //showToast("Home");
                found = true;
                break;
            case R.id.calendar:
                CalendarFragment cFragment = new CalendarFragment();
                FragmentTransaction cFragmentTransaction = getSupportFragmentManager().beginTransaction();
                //cFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                //Use of different animations in case of different previous fragment
                Fragment fragmentInstance = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                if(fragmentInstance instanceof HomeFragment){
                    cFragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                }
                else{
                    cFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                }
                cFragmentTransaction.replace(R.id.fragmentContainer, cFragment, "");
                cFragmentTransaction.addToBackStack(null); //ADDED!!!
                cFragmentTransaction.commit();
                //showToast("Sign in before!");
                found = true;
                break;
            case R.id.profile:
                Bundle bundleProfile = getUserInformationBundle();
                ProfileFragment pFragment = new ProfileFragment();
                if(bundleProfile != null) {
                    pFragment.setArguments(bundleProfile);
                }
                FragmentTransaction pFragmentTransaction = getSupportFragmentManager().beginTransaction();
                //pFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                pFragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                pFragmentTransaction.replace(R.id.fragmentContainer, pFragment, "");
                pFragmentTransaction.addToBackStack(null); //ADDED!!!
                pFragmentTransaction.commit();
                //showToast("Sign in before!");
                found = true;
                break;
            default:
        }

        mySelectedItem = item.getItemId();

        if(fragment != null){
            Bundle bundleHome = getPersonGivenNameBundle();
            HomeFragment hFragment = new HomeFragment();
            if(bundleHome != null){
                hFragment.setArguments(bundleHome);
            }
            FragmentTransaction hFragmentTransaction = getSupportFragmentManager().beginTransaction();
            //hFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
            hFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            hFragmentTransaction.replace(R.id.fragmentContainer, hFragment, "");
            hFragmentTransaction.addToBackStack(null); //ADDED!!!
            hFragmentTransaction.commit();
        }
        return found;
    }

    //Return a Bundle object containing user informations arguments (for ProfileFragment)
    public Bundle getUserInformationBundle(){
        Bundle bundle = new Bundle();
        //GoogleSignInAccount mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (this.mGoogleSignInAccount != null) {
            String personName = mGoogleSignInAccount.getDisplayName();
            String personGivenName = mGoogleSignInAccount.getGivenName();
            String personFamilyName = mGoogleSignInAccount.getFamilyName();
            String personEmail = mGoogleSignInAccount.getEmail();
            String personId = mGoogleSignInAccount.getId();
            Uri personPhoto = mGoogleSignInAccount.getPhotoUrl();
            bundle.putString("personName", personName);
            bundle.putString("personGivenName", personGivenName);
            bundle.putString("personFamilyName", personFamilyName);
            bundle.putString("personEmail", personEmail);
            bundle.putString("personId", personId);
            bundle.putParcelable("personPhoto", personPhoto);
            return bundle;
        }
        return null;
    }

    //Return a Bundle object containing personName argument (for HomeFragment)
    public Bundle getPersonGivenNameBundle(){
        Bundle bundle = new Bundle();
        if (this.mGoogleSignInAccount != null) {
            String personGivenName = mGoogleSignInAccount.getGivenName();
            bundle.putString("personGivenName", personGivenName);
            return bundle;
        }
        return null;
    }

    //Shouldn't be necessary to show these toast........
    public void showToast(String stringToShow){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
        if(stringToShow.equals("Home") || stringToShow.equals("We are in Home yet")) {
            image.setImageResource(R.drawable.baseline_home_20);
        }
        else{
            image.setImageResource(R.drawable.ic_toasticon);
        }
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(stringToShow);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = navigationView.getMenu().getItem(0);

        if (mySelectedItem != homeItem.getItemId()) {
            // Select home item
            navigationView.setSelectedItemId(homeItem.getItemId());
            selectFragment(homeItem);
            //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);  //isn't a good animation
        } else {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }

}