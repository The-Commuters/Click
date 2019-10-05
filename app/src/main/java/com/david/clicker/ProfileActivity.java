package com.david.clicker;

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
        emailField.setText("david.naist@mail.com");

        // new stuff
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itfag.usn.no/~216716/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProfileApi profileApi = retrofit.create(ProfileApi.class);

        Call<Profiles> call = profileApi.getProfiles();

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
