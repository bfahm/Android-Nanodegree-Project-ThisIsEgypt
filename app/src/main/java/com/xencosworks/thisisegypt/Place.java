package com.xencosworks.thisisegypt;

/**
 * Created by Bola on 2/3/2019.
 */

public class Place {
    private String mTitle;
    private String mDetails;
    private String mWikipedia;
    private String mGImages;
    private double mRating;
    private int mCoverPic;
    private boolean mIsLiked;
    private MapsLocation mLocation;

    public Place(String title, double rating, int coverPic, String details,
                 MapsLocation location, String wikipedia, String gImages ){
        mTitle = title;
        mRating = rating;
        mCoverPic = coverPic;
        mDetails = details;
        mIsLiked = false;
        mLocation = location;
        mWikipedia = wikipedia;
        mGImages = gImages;
    }

    public String[] getStrings(){
        String [] retVals = new String[2];
        retVals[0] = mTitle;
        retVals[1] = mDetails;
        return retVals;
    }

    public String getLinkWiki(){
        return mWikipedia;
    }

    public String getLinkGImgs(){
        return mGImages;
    }

    public double getRating(){
        return mRating;
    }

    public int getCoverPic(){
        return mCoverPic;
    }

    public String getLatitude(){
        return mLocation.getLatitude();
    }

    public String getLongitude(){
        return mLocation.getLongitude();
    }

    public boolean isLiked(){
        return mIsLiked;
    }

    public void setLiked(boolean status){
        mIsLiked = status;
    }


}
