package com.commuters.clicker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.repository.ProfileRepository;

public class MainViewModel extends AndroidViewModel {

    ProfileRepository profileRepository;

    LiveData<Profile> profile;



    public MainViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        profile = profileRepository.getLocalProfile();
    }

    public LiveData<Profile> getLocalProfile() {
        return profile;
    }


}
