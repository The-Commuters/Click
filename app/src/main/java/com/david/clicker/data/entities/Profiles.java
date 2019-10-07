package com.david.clicker.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profiles {
    @SerializedName("profiles")
    private List<Profile> profiles;

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
