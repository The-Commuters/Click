package com.david.clicker.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.david.clicker.R;
import com.david.clicker.data.entities.Profile;
import com.david.clicker.data.local.ProfileDao;
import com.david.clicker.ui.profile.ProfileViewModel;


public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;

    private ProfileViewModel profileViewModel;


    Profile test;
    ProfileDao profileDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);

        final Button loginButton = findViewById(R.id.login);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Login", "I was clicked");


                final Handler handler = new Handler();
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Profile profile = profileViewModel.getProfile(usernameEditText.getText().toString());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (usernameEditText.getText().toString() == profile.getEmail() && passwordEditText.getText().toString() == profile.getPassword())
                                    ;
                                Toast.makeText(getApplication(), "Welcome " + profile.getUsername(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })).start();


            }

        });

    }

}



