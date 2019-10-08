package com.david.clicker.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.david.clicker.data.entities.Profile;

@Dao
public interface ProfileDao {

    @Insert
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Query("DELETE FROM profile_table")
    void deleteProfile();

    @Query("SELECT * FROM profile_table")
    LiveData<Profile> getProfile();


}
