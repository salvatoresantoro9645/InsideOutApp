package com.example.insideout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class GPSActivity extends ActionBaseActivity implements OnMapReadyCallback {

    private static final String REQUESTING_LOCATION_UPDATES_KEY = "Req_Loc_Updates_Key";
    private static final String MOOD_TITLE = "MoodTitle";

    private GoogleMap myGoogleMap;
    private GoogleMapOptions optionsMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentMarker;
    private String mood;
    private boolean requestingLocationUpdates = true;

    double currentLat;
    double currentLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_music); using abstract method getLayoutResourceId

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.mood = bundle.getString(MOOD_TITLE);
            Log.e("MOOD_TITLE", this.mood);
        }
        updateValuesFromBundle(savedInstanceState);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        optionsMap = new GoogleMapOptions();
        optionsMap.mapType(GoogleMap.MAP_TYPE_HYBRID)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .zoomControlsEnabled(true)
                .zoomGesturesEnabled(true)
                .tiltGesturesEnabled(false);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance(optionsMap);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                requestingLocationUpdates);
        // ...
        super.onSaveInstanceState(outState);
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        // Update the value of requestingLocationUpdates from the Bundle.
        if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
            requestingLocationUpdates = savedInstanceState.getBoolean(
                    REQUESTING_LOCATION_UPDATES_KEY);
        }
        //updateUI();
    }


    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        if (fusedLocationClient != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
            Log.e("ON_RESUME_INSIDE", mood);
        }
        Log.e("ON_RESUME_OUTSIDE", mood);
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.myLooper());
    }


    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_gps;
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.myGoogleMap = googleMap;

        locationRequest = LocationRequest.create()
                //.setInterval(120000) //two minutes
                //.setFastestInterval(60000) //one minute
                .setSmallestDisplacement(1000) //one kilometer
                .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper()); //myLooper() before
                myGoogleMap.setMyLocationEnabled(true);
            }
            else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else{
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
                myGoogleMap.setMyLocationEnabled(true);
        }
    }


    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult == null){
                return;
            }
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                Location currentLocation = locationList.get(locationList.size() - 1);
                lastLocation = currentLocation;
                if (currentMarker != null) {
                    currentMarker.remove();
                }

                currentLat = currentLocation.getLatitude();
                currentLng = currentLocation.getLongitude();

                AddressBuilder addressBuilder = new AddressBuilder(mood);
                StringBuilder sbValue = new StringBuilder(addressBuilder.getStringBuilder(currentLat, currentLng));
                PlacesTask placesTask = new PlacesTask();
                placesTask.execute(sbValue.toString());

                /*
                LatLng currentLatLng = new LatLng(currentLat, currentLng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(currentLatLng);
                markerOptions.title("Current Position");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                currentMarker = myGoogleMap.addMarker(markerOptions);
                 */

                Log.e("BEFORE_MOVE_CAMERA", currentLat+","+currentLng);
                LatLng currentLatLng = new LatLng(currentLat, currentLng);
                myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 11));
            }
        }
    };


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(GPSActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    public class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());  //Debug
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParserTask
            parserTask.execute(result);
        }

        private String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();

                br.close();

            } catch (Exception e) {
                Log.d("Exception while downloading url", e.toString()); //Debug
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            Log.e("DOWNLOAD_URL", "DATA "+data);
            return data;
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            Place_JSON placeJson = new Place_JSON();

            try {
                jObject = new JSONObject(jsonData[0]);

                places = placeJson.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {

            Log.d("Map", "list size: " + list.size());
            // Clears all the existing markers;
            myGoogleMap.clear();

            for (int i = 0; i < list.size(); i++) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);

                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");

                Log.d("Map", "place: " + name);

                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");

                LatLng latLng = new LatLng(lat, lng);

                // Setting the position for the marker
                markerOptions.position(latLng);

                markerOptions.title(name + " : " + vicinity);

                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                // Placing a marker on the touched position
                Marker m = myGoogleMap.addMarker(markerOptions);
            }

            /*
            LatLng currentLatLng = new LatLng(currentLat, currentLng);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(currentLatLng);
            markerOptions.title("Your Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            currentMarker = myGoogleMap.addMarker(markerOptions);
             */
        }
    }

}