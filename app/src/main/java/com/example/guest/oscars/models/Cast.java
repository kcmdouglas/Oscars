package com.example.guest.oscars.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 3/24/16.
 */
@Parcel
public class Cast {
    Integer castId;
    String mName;
    String mProfile;

    public Cast(Integer castId, String mName, String mProfile) {
        this.castId = castId;
        this.mName = mName;
        this.mProfile = mProfile;
    }
    public Cast() {

    }

    public Integer getCastId() {
        return castId;
    }

    public String getmName() {
        return mName;
    }

    public String getmProfile() {
        return mProfile;
    }
}
