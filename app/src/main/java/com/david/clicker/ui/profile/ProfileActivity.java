package com.david.clicker.ui.profile;

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

import com.david.clicker.R;
import com.david.clicker.data.entities.Profile;
import com.david.clicker.data.entities.Profiles;
import com.david.clicker.data.gameApi.GameApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    private TextView scoreField;
    private TextView usernameField;
    private TextView emailField;

    public static String EXTRA_SCORE = "score";
    public static String EXTRA_USERNAME = "username";
    public static String EXTRA_EMAIL = "email";
    public static String EXTRA_PASSWORD = "password";

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

        scoreField.setText(Integer.toString(1000));
        usernameField.setText("David");
        emailField.setText("");

        // new stuff
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itfag.usn.no/~216716/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        gameApi = retrofit.create(GameApi.class);

        // createProfile();
        // updateProfile();
        readProfiles();
        // readProfile();
        // deleteProfile();
    }

    private void readProfile() {
        Call<Profile> call = gameApi.readProfile("david");

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (!response.isSuccessful()) {
                    emailField.setText("Code: " + response.code());
                    return;
                }

                Profile profile = response.body();

                String content = "";
                content += "Username: " + profile.getUsername() + "\n";
                content += "Email: " + profile.getEmail() + "\n\n";

                emailField.append(content);

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                emailField.setText(t.getMessage());
            }
        });
    }

    private void readProfiles() {
        Call<Profiles> call = gameApi.readProfiles();

        call.enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                if (!response.isSuccessful()) {
                    emailField.setText("Code: " + response.code());
                    return;
                }

                Profiles profiles = response.body();

                String content = "";
                for (Profile profile: profiles.getProfiles()) {
                    content += "Username: " + profile.getUsername() + "\n";
                    content += "Email: " + profile.getEmail() + "\n\n";
                }
                emailField.append(content);
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                emailField.setText(t.getMessage());
            }
        });
    }

    private void createProfile() {
        Profile profile = new Profile("Success", "some@email.com", "passsadswod");

        Call<Integer> call = gameApi.createProfile(profile);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                emailField.setText("Code: " + response.code() + ", Failure");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                emailField.setText(t.getMessage());
            }
        });
    }

    private void updateProfile() {
        Profile profile = new Profile(null, "Long email that is reallyyyy long", null);

        Call<Integer> call = gameApi.updateProfile("david_2", profile);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                emailField.setText("Code: " + response.code() + ", Failure");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                emailField.setText(t.getMessage());
            }
        });
    }

    private void deleteProfile() {
        Call<Integer> call = gameApi.deleteProfile("david_2");

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                emailField.setText("Code: " + response.code() + ", Failure");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                emailField.setText(t.getMessage());
            }
        });
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

        if (username.trim().isEmpty()) {
            toast("Please enter a username");
            return;
        }

        if (email.trim().isEmpty()) {
            toast("Please enter an email");
            return;
        }

        if (password.trim().isEmpty()) {
            toast("Please enter a password");
            return;
        }

        profileViewModel.insert(new Profile(username, email, password));
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
