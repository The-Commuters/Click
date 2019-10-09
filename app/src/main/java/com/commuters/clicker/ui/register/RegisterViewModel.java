package com.commuters.clicker.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.repository.ProfileRepository;

import retrofit2.Callback;

public class RegisterViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;
    private LiveData<Profile> localProfile;


    public RegisterViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        localProfile = profileRepository.getLocalProfile();
    }

  public void readOnlineProfile(String username, Callback<Profile> callback) {
      profileRepository.readOnlineProfile(username, callback );
  }

    public void createOnlineProfile(Profile profile, Callback<Void> callback) {
        profileRepository.createOnlineProfile(profile, callback );
    }


  public LiveData<Profile> getLocalProfile(){
        return  localProfile;
  }

  public void deleteLocalProfile() {
        profileRepository.deleteLocalProfile();
  }
    public void insert(Profile profile) {
        profileRepository.insertLocalProfile(profile);
    }
}
