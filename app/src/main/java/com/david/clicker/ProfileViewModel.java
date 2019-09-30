package com.david.clicker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;

    private LiveData<List<Profile>> allProfiles;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        allProfiles = profileRepository.getAllNotes();
    }

    public void insert(Profile profile) {
        profileRepository.insert(profile);
    }

    public void update(Profile profile) {
        profileRepository.update(profile);
    }

    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    public void deleteAllNotes() {
        profileRepository.deleteAllNotes();
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
}
