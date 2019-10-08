package com.commuters.clicker.ui.profile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.commuters.clicker.R;
import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.gameApi.GameApi;

public class ProfileActivity extends AppCompatActivity {
    private TextView scoreField;
    private TextView usernameField;
    private TextView emailField;

    public static String EXTRA_SCORE = "score";
    public static String EXTRA_USERNAME = "username";
    public static String EXTRA_EMAIL = "email";

    private Bundle extras;
    private ProfileViewModel profileViewModel;

    // New Stuff
    private GameApi gameApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        extras = getIntent().getExtras();
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        scoreField = findViewById(R.id.text_view_score);
        usernameField = findViewById(R.id.text_view_username);
        emailField = findViewById(R.id.text_view_email);

        String score = Integer.toString(extras.getInt(EXTRA_SCORE));
        String username = extras.getString(EXTRA_USERNAME);
        String email = extras.getString(EXTRA_EMAIL);

        scoreField.setText(score);
        usernameField.setText(username);
        emailField.setText(email);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.close_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void registerProfile() {
        String username = "";
        String email = "";
        String password = "";

        if (username == null || username.trim().isEmpty()) {
            toast("Please enter a username");
            return;
        }

        if (email.trim().isEmpty()) {
            toast("Please enter an email");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            toast("Please enter a password");
            return;
        }
        profileViewModel.insert(new Profile(username, email, password));
    }

    private boolean validateProfile(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            toast("Please enter a username");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            toast("Please enter a password");
            return false;
        }

        return true;
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
