package com.david.clicker.data.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "profile_table", indices = {@Index(value = "username", unique = true)})
public class Profile {
    @NonNull
    @PrimaryKey
    private String username;

    private String email;

    private String password;

    private int score;

    private int click_strength;

    private int combo_multiplier;

    private int combo_speed;

    public Profile(
            String username,
            String email,
            String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.score = 0;
        this.click_strength = 0;
        this.combo_multiplier = 0;
        this.combo_speed = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setClick_strength(int click_strength) {
        this.click_strength = click_strength;
    }

    public void setCombo_multiplier(int combo_multiplier) {
        this.combo_multiplier = combo_multiplier;
    }

    public void setCombo_speed(int combo_speed) {
        this.combo_speed = combo_speed;
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

    public int getScore() {
        return score;
    }

    public int getClick_strength() {
        return click_strength;
    }

    public int getCombo_multiplier() {
        return combo_multiplier;
    }

    public int getCombo_speed() {
        return combo_speed;
    }
}
