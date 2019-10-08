package com.commuters.clicker.ui.highscore;

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

public class HighscoreViewModel extends AndroidViewModel {

    private ProfileRepository profileRepository;

    private MutableLiveData<List<Profile>> highscorers = new MutableLiveData<List<Profile>>();

    public HighscoreViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        profileRepository.readOnlineProfiles(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                List<Profile> body = response.body().getProfiles();
                highscorers.setValue(body.size() > 10 ? body.subList(0, 10) : body);
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                highscorers.setValue(new ArrayList<Profile>());
                Log.e("API", "onFailure: failed to read highscorers", t);
            }
        });
    }

    public void insert(Profile profile) {
        profileRepository.insertLocalProfile(profile);
    }



    public LiveData<List<Profile>> getHighscorers() {
        return highscorers;
    }
}
