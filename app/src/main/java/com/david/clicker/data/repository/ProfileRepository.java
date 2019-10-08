package com.david.clicker.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.david.clicker.data.entities.Profile;
import com.david.clicker.data.entities.Profiles;
import com.david.clicker.data.gameApi.GameApi;
import com.david.clicker.data.local.ProfileDao;
import com.david.clicker.data.local.ProfileDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileRepository {
    private ProfileDao profileDao;

    GameApi gameApi;

    private LiveData<Profile> profile;

    public ProfileRepository(Application application){
        ProfileDatabase database = ProfileDatabase.getInstance(application);
        profileDao = database.profileDao();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itfag.usn.no/~216716/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gameApi = retrofit.create(GameApi.class);

        profile = profileDao.getProfile();
    }

    public void insertLocalProfile(Profile profile) {
        new insertLocalProfileAsyncTask(profileDao).execute(profile);
    }

    public void updateLocalProfile(Profile profile) {
        new updateLocalProfileAsyncTask(profileDao).execute(profile);
    }

    public void deleteLocalProfile() {
        new deleteLocalProfilesAsyncTask(profileDao).execute();
    }

    public LiveData<Profile> getLocalProfile() {
        return profile;
    }

    public void createOnlineProfile(Profile profile, Callback<Void> callback) {
        Call<Void> call = gameApi.createProfile(profile);
        call.enqueue(callback);
    }

    public void readOnlineProfile(String username, Callback<Profile> callback) {
        Call<Profile> call = gameApi.readProfile(username);
        call.enqueue(callback);
    }

    public void readOnlineProfiles(Callback<Profiles> callback) {
        Call<Profiles> call = gameApi.readProfiles();
        call.enqueue(callback);
    }

    public void updateOnlineProfile(String username, Profile profile, Callback<Void> callback) {
        Call<Void> call = gameApi.updateProfile(username, profile);
        call.enqueue(callback);
    }

    public void deleteOnlineProfile(String username, Callback<Void> callback) {
        Call<Void> call = gameApi.deleteProfile(username);
        call.enqueue(callback);
    }

    private static class insertLocalProfileAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao profileDao;

        private insertLocalProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    private static class updateLocalProfileAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao profileDao;

        private updateLocalProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }

    private static class deleteLocalProfilesAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProfileDao profileDao;

        private deleteLocalProfilesAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Void ...voids) {
            profileDao.deleteProfile();
            return null;
        }
    }





}
