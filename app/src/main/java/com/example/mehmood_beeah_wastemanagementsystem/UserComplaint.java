package com.example.mehmood_beeah_wastemanagementsystem;

import com.google.firebase.database.Exclude;

public class UserComplaint {

    public String compName;
    public String compDetail;

    public String compUrl;

    public String mKey;

    public UserComplaint() {
    }

    public UserComplaint(String compName, String compDetail, String compUrl) {
        this.compName = compName;
        this.compDetail = compDetail;
        this.compUrl = compUrl;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompDetail() {
        return compDetail;
    }

    public void setCompDetail(String compDetail) {
        this.compDetail = compDetail;
    }

    public String getCompUrl() {
        return compUrl;
    }

    public void setCompUrl(String compUrl) {
        this.compUrl = compUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
