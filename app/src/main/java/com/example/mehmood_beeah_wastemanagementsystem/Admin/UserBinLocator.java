package com.example.mehmood_beeah_wastemanagementsystem.Admin;

import com.google.firebase.database.Exclude;

public class UserBinLocator {

    public String binType;
    public String binName;
    public String binAddress;

    public String mKey;

    public UserBinLocator() {
    }

    public UserBinLocator(String binType, String binName, String binAddress) {
        this.binType = binType;
        this.binName = binName;
        this.binAddress = binAddress;
    }

    public String getBinType() {
        return binType;
    }

    public void setBinType(String binType) {
        this.binType = binType;
    }

    public String getBinName() {
        return binName;
    }

    public void setBinName(String binName) {
        this.binName = binName;
    }

    public String getBinAddress() {
        return binAddress;
    }

    public void setBinAddress(String binAddress) {
        this.binAddress = binAddress;
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
