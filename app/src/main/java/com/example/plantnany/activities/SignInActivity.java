package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantnany.R;

public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.btn_sign_in_google).setOnClickListener(this::googleSingIn);
        findViewById(R.id.btn_sign_in_email).setOnClickListener(this::emailSingIn);
        findViewById(R.id.btn_sign_in_facebook).setOnClickListener(this::facebookSingIn);
        findViewById(R.id.btn_sign_in_twitter).setOnClickListener(this::twitterSingIn);
    }

    private void twitterSingIn(View view) {

    }

    private void facebookSingIn(View view) {

    }

    private void emailSingIn(View view) {
        startActivity(new Intent(this, EmailSignInActivity.class));
    }

    private void googleSingIn(View view) {
        startActivity(new Intent(this, GoogleSigninActivity.class));
    }
}