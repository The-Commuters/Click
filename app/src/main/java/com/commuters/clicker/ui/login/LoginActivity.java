package com.commuters.clicker.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.commuters.clicker.R;
import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.local.ProfileDao;
import com.commuters.clicker.ui.register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Profile test;

    String password;
    String username;

    Profile profile;

    static final int REGISTER = 2;

    ProfileDao profileDao;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);

        final Button loginButton = findViewById(R.id.login);

        final Button registerButton = findViewById(R.id.register);



        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);




    }

    public void register(View v) {
        Intent registerIntent = new Intent(this, RegisterActivity.class);

        startActivityForResult(registerIntent, REGISTER);
    }

    public void login(View v) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();


        loginViewModel.readOnlineProfile(username, new Callback<Profile>() {
            @Override
            public void onResponse(final Call<Profile> call, final Response<Profile> response) {


                profile = response.body();

                if (response.isSuccessful()) {

                    if (username.equals(profile.getUsername()) && password.equals(profile.getPassword())) {

                        loginViewModel.deleteLocalProfile();
                        loginViewModel.insert(profile);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }

        });
    }


}
