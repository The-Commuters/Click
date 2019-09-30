package com.david.clicker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Upgrade {
    @PrimaryKey
    @ForeignKey(entity = Profile.class, parentColumns = "id", childColumns = "user_id")
    private int user_id;

    private int click_value_level;

    private int combo_multiplier_level;

    private int combo_speed_level;

    public Upgrade(int user_id, int click_value_level, int combo_multiplier_level, int combo_speed_level) {
        this.user_id = user_id;
        this.click_value_level = click_value_level;
        this.combo_multiplier_level = combo_multiplier_level;
        this.combo_speed_level = combo_speed_level;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getClick_value_level() {
        return click_value_level;
    }

    public int getCombo_multiplier_level() {
        return combo_multiplier_level;
    }

    public int getCombo_speed_level() {
        return combo_speed_level;
    }
}
