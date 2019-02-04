package com.xencosworks.thisisegypt;

/**
 * Created by Bola on 2/4/2019.
 */

public class MapsLocation {
    private String mLatitude;
    private String mLongitude;

    public MapsLocation(String latitude, String longitude){
        mLongitude = longitude;
        mLatitude = latitude;
    }

    public String getLatitude(){
        return mLatitude;
    }

    public String getLongitude(){
        return mLongitude;
    }

    public String getBundledData(){
        return mLatitude+", "+mLongitude;
    }
}
