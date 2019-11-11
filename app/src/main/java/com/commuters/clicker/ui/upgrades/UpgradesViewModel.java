package com.commuters.clicker.ui.upgrades;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.repository.ProfileRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpgradesViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;
    private LiveData<Profile> localProfile;


    public UpgradesViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        localProfile = profileRepository.getLocalProfile();
    }

    public LiveData<Profile> getLocalProfile() {
        return localProfile;
    }

    public void update(Profile profile) {
        profileRepository.updateOnlineProfile(profile.getUsername(), profile, new Callback<Void>(){

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        } );

        profileRepository.updateLocalProfile(profile);
    }
}