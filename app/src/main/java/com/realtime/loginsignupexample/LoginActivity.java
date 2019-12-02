package com.realtime.loginsignupexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppDatabase appDatabase;
    EditText edtEmail, edtPassword;
    Button btnLogin;
    List<User> users;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setOnClick();
    }

    private void setOnClick() {
        btnLogin.setOnClickListener(this);
    }


    private void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdatabase").allowMainThreadQueries().build();

    }

    public void goToSignupPage(View view) {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogin:
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (password.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    users = appDatabase.userDao().getUserByEmail(email);
                    if (users.isEmpty()) {
                        Toast.makeText(this, "No user found with this email ID", Toast.LENGTH_SHORT).show();
                    } else {
                        if (email.equalsIgnoreCase(users.get(0).getEmail().trim()) && password.equalsIgnoreCase(users.get(0).getPassword().trim())) {
                            Toast.makeText(this, "Login Successfully .", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        } else {
                            Toast.makeText(this, "Invalid user login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
        }

    }
}
