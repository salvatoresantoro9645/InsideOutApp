package com.example.insideout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MAIN_ACTIVITY";
    private static final int RC_SIGN_IN = 1; //Constant to say "request ok"
    private SignInButton signInButton;
    private BottomNavigationView navigationView;
    private GoogleSignInClient mGoogleSignInClient;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareBottomNavigationView();
        navigationView.setSelectedItemId(R.id.home);

        // Set the dimensions of the sign-in button
        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(this);


        // Configure sign-in to request the user's ID, email address, and basic profile
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account){
        if(account != null){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.sign_in_error, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInButton:
                signIn();
                break;
            default:
                //no operation to perform
        }
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void prepareBottomNavigationView(){
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigationView.setVisibility(View.VISIBLE);
        navigationView.setOnItemSelectedListener(
                item -> {
                    boolean found = false;
                    switch (item.getItemId()) {
                        case R.id.home:
                            //showToast("Home");  //Debug
                            found = true;
                            break;
                        case R.id.calendar:
                            showToast("Sign in before!");
                            found = false;
                            break;
                        case R.id.profile:
                            showToast("Sign in before!");
                            found = false;
                            break;
                        default:
                            //Do Nothing
                    }
                    return found;
                }
        );

        navigationView.setOnItemReselectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.home:
                            //showToast("We are in Home yet");  //Debug
                            //Toast.makeText(context, "We are in home yet", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.calendar:
                            showToast("Sign in before!");
                            //Toast.makeText(context, "Calendar : Sign in before...", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.profile:
                            showToast("Sign in before!");
                            //Toast.makeText(context, "Profile : Sign in before...", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            //Do Nothing
                    }
                }
        );
    }

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

    //Used to don't use back button from starting in main activity
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}