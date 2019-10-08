package com.commuters.clicker.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.repository.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
    }

    public void insert(Profile profile) {
        profileRepository.insertLocalProfile(profile);
    }
}
