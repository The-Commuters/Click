package com.david.clicker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileRepository {
    private ProfileDao profileDao;

    private LiveData<List<Profile>> allProfiles;

    public ProfileRepository(Application application){
        ProfileDatabase database = ProfileDatabase.getInstance(application);
        profileDao = database.profileDao();
        allProfiles = profileDao.getAllProfiles();
    }

    public void insert(Profile profile) {
        new insertProfileAsyncTask(profileDao).execute(profile);
    }

    public void update(Profile profile) {
        new updateProfileAsyncTask(profileDao).execute(profile);
    }

    public void delete(Profile profile) {
        new deleteProfileAsyncTask(profileDao).execute(profile);
    }

    public void deleteAllProfiles() {
        new deleteAllProfilesAsyncTask(profileDao).execute();
    }

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }

    private static class insertProfileAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao profileDao;

        private insertProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insert(profiles[0]);
            return null;
        }
    }

    private static class updateProfileAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao profileDao;

        private updateProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.update(profiles[0]);
            return null;
        }
    }

    private static class deleteProfileAsyncTask extends AsyncTask<Profile, Void, Void> {

        private ProfileDao profileDao;

        private deleteProfileAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.delete(profiles[0]);
            return null;
        }
    }

    private static class deleteAllProfilesAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProfileDao profileDao;

        private deleteAllProfilesAsyncTask(ProfileDao profileDao) {
            this.profileDao = profileDao;
        }

        @Override
        protected Void doInBackground(Void ...voids) {
            profileDao.deleteAllProfiles();
            return null;
        }
    }

}
