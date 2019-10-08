package com.commuters.clicker.data.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.commuters.clicker.data.entities.Profile;

@Database(entities = Profile.class, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    private static ProfileDatabase instance;

    public abstract ProfileDao profileDao();

    public static synchronized ProfileDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProfileDatabase.class,
                    "profile_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDatabaseAsyncTask(instance).execute();
        }
    };

    private static class populateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProfileDao profileDao;

        private populateDatabaseAsyncTask(ProfileDatabase profileDatabase) {
            this.profileDao = profileDatabase.profileDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            profileDao.insert(new Profile("Ripmoff", "David.Naist@gmail.com", "12345"));
            return null;
        }
    }

}
