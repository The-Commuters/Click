package com.david.clicker;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView scoreField;
    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;

    public static String EXTRA_SCORE = "score";
    public static String EXTRA_USERNAME = "username";
    public static String EXTRA_EMAIL = "email";
    public static String EXTRA_PASSWORD = "password";

    private Bundle extras;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        extras = getIntent().getExtras();

        scoreField = findViewById(R.id.score_text_view);
        usernameField = findViewById(R.id.edit_text_username);
        emailField = findViewById(R.id.edit_text_email);
        passwordField = findViewById(R.id.edit_text_password);

        scoreField.setText(Integer.toString(1000));
        buildField(usernameField, "David", true);
        buildField(emailField, "david.naist@mail.com", true);
        buildField(passwordField, "Password", true);
    }

    public void buildField(EditText field, String text, Boolean disabled) {
        field.setText(text);
        if (disabled) {
            field.setEnabled(false);
            field.setInputType(InputType.TYPE_NULL);
        }
    }
}
