package com.david.clicker.data.gameApi;

import com.david.clicker.data.entities.Profile;
import com.david.clicker.data.entities.Profiles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameApiAccesser {
    GameApi gameApi;

    public GameApiAccesser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itfag.usn.no/~216716/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gameApi = retrofit.create(GameApi.class);
    }

    public void createProfile(Profile profile, Callback<Void> callback) {
        Call<Void> call = gameApi.createProfile(profile);
        call.enqueue(callback);
    }

    public void readProfile(String username, Callback<Profile> callback) {
        Call<Profile> call = gameApi.readProfile(username);
        call.enqueue(callback);
    }

    public void readProfiles(Callback<Profiles> callback) {
        Call<Profiles> call = gameApi.readProfiles();
        call.enqueue(callback);
    }

    public void updateProfile(String username, Profile profile, Callback<Void> callback) {
        Call<Void> call = gameApi.updateProfile(username, profile);
        call.enqueue(callback);
    }

    public void deleteProfile(String username, Callback<Void> callback) {
        Call<Void> call = gameApi.deleteProfile(username);
        call.enqueue(callback);
    }
}
