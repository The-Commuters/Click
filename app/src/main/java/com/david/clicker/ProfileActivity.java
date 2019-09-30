package com.david.clicker;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

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

        TextView scoreField = findViewById(R.id.score_text_view);
        Integer score = extras.getInt(EXTRA_SCORE, 0);
        scoreField.setText(score.toString());

        EditText usernameField = findViewById(R.id.edit_text_username);
        renderField(usernameField, EXTRA_USERNAME, true);

        EditText emailField = findViewById(R.id.edit_text_email);
        renderField(emailField, EXTRA_EMAIL, true);

        EditText passwordField = findViewById(R.id.edit_text_password);
        renderField(passwordField, EXTRA_PASSWORD, true);
    }

    public void renderField(EditText field, String input, Boolean disabled) {
        String data = extras.getString(input, "Processing");
        field.setText(data);
        if (disabled) {
            field.setEnabled(false);
            field.setInputType(InputType.TYPE_NULL);
        }
    }
}
