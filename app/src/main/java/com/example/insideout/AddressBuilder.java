package com.example.insideout;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AddressBuilder {

    private static final String MAPS_API_KEY = "****************************";
    String mood;

    public AddressBuilder(String mood){
        this.mood = mood;
    }

    public StringBuilder getStringBuilder(double lat, double lng){

        //use current location here
        double myLat = lat;
        double myLng = lng;
        String types = "";

        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + myLat + "," + myLng);
        sb.append("&radius=5000");
        types = getTypes();
        try{
            sb.append("&types="+ URLEncoder.encode(types, "UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        sb.append("&key="+MAPS_API_KEY);  //This is my API KEY
        sb.append("&sensor=true");

        //Log.d("Map", "api: " + sb.toString());  //Debug

        return sb;
    }

    public String getTypes(){
        String types = "";
        //max 5 different 'Place Types' from table 1 and table 2
        switch(this.mood){
            case "JOY":
                types = types.concat("park|tourist_attraction");
                break;
            case "SORROW":
                types = types.concat("church|movie_theater");
                break;
            case "ANGER":
                types = types.concat("spa|hospital|church");
                break;
            case "DISGUST":
                types = types.concat("park|museum");
                break;
            case "FEAR":
                types = types.concat("police|hospital");
                break;
            case "HUNGRY":
                types = types.concat("restaurant");
                break;
        }
        return types;
    }

}
