package com.example.plantnany.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.plantnany.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        progressDialog();
        hideProgressDialog();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateUI();
            }
        };

        binding.tvSignUp.setOnClickListener(this::signUp);
        binding.btnSignIn.setOnClickListener(this::signIn);

    }

    private void progressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("User is creating");
        mProgressDialog.setMessage("Please Waite...");
        mProgressDialog.setProgress(0);
    }

    private void signIn(View view) {
        if (!validateEmailAddress() | !validatePassword()) {
            // Email or Password not valid,
            return;
        }
        //Email and Password valid, sign in user here

        String email = binding.etEmail.getEditText().getText().toString().trim();
        String password = binding.etPassword.getEditText().getText().toString().trim();

        showProgressBar();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(SignInActivity.this, "User Logged in", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                Toast.makeText(SignInActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();

                                binding.etPassword.setError("Password Incorrect");
                            } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                binding.etEmail.setError("Email Incorrect");
                                Toast.makeText(SignInActivity.this, "Email Incorrect", Toast.LENGTH_SHORT).show();

                            }

                        }
                        hideProgressDialog();

                    }
                });
    }

    private boolean validateEmailAddress() {

        String email = binding.etEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            binding.etEmail.setError("Email is required. Can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Invalid Email. Enter valid email address.");
            return false;
        } else {
            binding.etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String password = binding.etPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            binding.etPassword.setError("Password is required. Can't be empty.");
            return false;
        } else if (password.length() < 6) {
            binding.etPassword.setError("Password length short. Minimum 6 characters required.");
            return false;
        } else {
            binding.etPassword.setError(null);
            return true;
        }
    }

    private void showProgressBar() {
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not login", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "User login", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthListener != null) {
            if (mAuth != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }
    }

    private void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}