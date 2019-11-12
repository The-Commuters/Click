package com.commuters.clicker.data.entities;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_table", indices = {@Index(value = "username", unique = true)})
public class Profile extends BaseObservable {

    // Clicking constants
    final static int CLICK_STRENGTH_BASE = 1; // hvor mangen poeng som blir tjent per click
    final static int CLICK_STRENGTH_MAXIMUM = 20; // maximumen til den over
    final static int COUNTER_MINIMUM = 0;

    // Cost-variables
    final static int CLICK_STRENGTH_COST_BASE = 10;
    final static int COMBO_MULTIPLIER_COST_BASE = 80;
    final static int COMBO_SPEED_COST_BASE = 200;

    // Constants to calculate the cost of upgrades
    final static double CLICK_STRENGTH_GROWTH_RATE = 1.2;
    final static double COMBO_MULTIPLIER_GROWTH_RATE = 0.5;
    final static double COMBO_SPEED_GROWTH_RATE = 0.3;

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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Bindable
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        notifyPropertyChanged(BR.score);
        this.score = score;
    }

    @Bindable
    public int getClick_strength() {
        return click_strength;
    }

    public void setClick_strength(int click_strength) {
        notifyPropertyChanged(BR.click_strength);
        this.click_strength = click_strength;
    }

    @Bindable
    public int getCombo_multiplier() {
        return combo_multiplier;
    }

    public void setCombo_multiplier(int combo_multiplier) {
        notifyPropertyChanged(BR.combo_multiplier);
        this.combo_multiplier = combo_multiplier;
    }

    @Bindable
    public int getCombo_speed() {
        return combo_speed;
    }

    public void setCombo_speed(int combo_speed) {
        this.combo_speed = combo_speed;
    }

    @Bindable
    public int getClickStrengthCost() {
        notifyPropertyChanged(BR.clickStrengthCost);
        return (int) (CLICK_STRENGTH_COST_BASE * (CLICK_STRENGTH_GROWTH_RATE * click_strength));
    }

    public void incrementClickStrength() {
        if(this.getScore() >= this.getClickStrengthCost()){
            this.setScore(score - this.getClickStrengthCost());
            this.setClick_strength(click_strength + 1);
        }
    }
    }

