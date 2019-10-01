package com.david.clicker;


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

    private int click_strength_level;

    private int combo_multiplier_level;

    private int combo_speed_level;

    public Profile(
            String username,
            String email,
            String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.score = 0;
        this.click_strength_level = 0;
        this.combo_multiplier_level = 0;
        this.combo_speed_level = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setClick_strength_level(int click_strength_level) {
        this.click_strength_level = click_strength_level;
    }

    public void setCombo_multiplier_level(int combo_multiplier_level) {
        this.combo_multiplier_level = combo_multiplier_level;
    }

    public void setCombo_speed_level(int combo_speed_level) {
        this.combo_speed_level = combo_speed_level;
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

    public int getClick_strength_level() {
        return click_strength_level;
    }

    public int getCombo_multiplier_level() {
        return combo_multiplier_level;
    }

    public int getCombo_speed_level() {
        return combo_speed_level;
    }
}
