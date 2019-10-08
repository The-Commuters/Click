package com.david.clicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.david.clicker.data.entities.Profile;
import com.david.clicker.ui.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private Profile localProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_game, R.id.navigation_upgrade, R.id.navigation_highscore)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getLocalProfile().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                localProfile = profile;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_profile:
                showProfile();
                return true;

            case R.id.open_settings:
                showSettings();
                return true;

            default: return super.onOptionsItemSelected(item);

        }
    }

    private void showSettings() {
        Toast.makeText(this, "Open settings", Toast.LENGTH_SHORT);
    }

    private void showProfile() {
        Profile profile = localProfile;
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_SCORE, profile.getScore());
        intent.putExtra(ProfileActivity.EXTRA_USERNAME, profile.getUsername());
        intent.putExtra(ProfileActivity.EXTRA_EMAIL, profile.getEmail());
        startActivity(intent);
    }

}
