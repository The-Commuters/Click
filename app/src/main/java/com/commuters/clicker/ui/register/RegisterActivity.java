package com.commuters.clicker.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.commuters.clicker.R;
import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.local.ProfileDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {



    EditText usernameEditText;
    EditText passwordEditText;
    EditText emailEditText;


    String password;
    String username;
    String email;

    Profile profile;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);
        emailEditText = findViewById(R.id.edit_text_email);

        final Button registerButton = findViewById(R.id.register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

    }

    public void register(View v) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        email = emailEditText.getText().toString();


        registerViewModel.createOnlineProfile( new Profile(username, email , password ), new Callback<Void>() {
            @Override
            public void onResponse(final Call<Void> call, final Response<Void> response) {
                finish();


            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }

        });
    }


}
