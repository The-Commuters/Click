package com.commuters.clicker.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.entities.Profiles;
import com.commuters.clicker.data.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;
    private LiveData<Profile> localProfile;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        localProfile = profileRepository.getLocalProfile();
    }

  public void readOnlineProfile(String username, Callback<Profile> callback) {
      profileRepository.readOnlineProfile(username, callback );
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
