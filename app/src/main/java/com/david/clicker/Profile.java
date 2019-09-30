package com.david.clicker;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "profile_table")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String email;

    private String password;

    private int permissions;

    private int score;

    public Profile(String username, String email, String password, int permissions, int score) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPermissions() {
        return permissions;
    }

    public int getScore() {
        return score;
    }
}
