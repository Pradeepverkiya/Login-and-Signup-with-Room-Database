package com.realtime.loginsignupexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    AppDatabase appDatabase;
    EditText edtFullName, edtPassword, edtEmail;
    Button btnSignup;
    User user;
    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setOnClick();
    }

    private void setOnClick()
    {
        btnSignup.setOnClickListener(this);
    }

    private void init() {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdatabase").allowMainThreadQueries().build();
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignup);
        user = new User();
    }

    public void goToLoinPage(View view) {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignup:
                saveUserData();
                break;
        }

    }

    private void saveUserData() {
        String fullName, email, password;

        fullName = edtFullName.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);

        appDatabase.userDao().insertUser(user);
        Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

        edtPassword.setText("");
        edtEmail.setText("");
        edtFullName.setText("");
    }
}
