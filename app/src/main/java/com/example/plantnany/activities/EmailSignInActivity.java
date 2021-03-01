package com.example.plantnany.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantnany.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class EmailSignInActivity extends AppCompatActivity {

    private TextInputLayout mEmailLayout, mPasswordLayout;
    private Button mBtnSignin, mBtnRegisterUser, mBtnSignOut;
    private TextView mOutputText;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_firebase_auth);
        initViews();

        mAuth = FirebaseAuth.getInstance();
        mBtnSignin.setOnClickListener(this::singInUser);
        mBtnRegisterUser.setOnClickListener(this::createUser);
        mBtnSignOut.setOnClickListener(this::signOutUser);

        hideProgressBar();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateUI();
            }
        };

    }

    private void signOutUser(View view) {
        mAuth.signOut();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void singInUser(View view) {

        if (!validateEmailAddress() | !validatePassword()) {
            // Email or Password not valid,
            return;
        }
        //Email and Password valid, sign in user here

        String email = mEmailLayout.getEditText().getText().toString().trim();
        String password = mPasswordLayout.getEditText().getText().toString().trim();

        showProgressBar();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(EmailSignInActivity.this, "User Logged in", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                Toast.makeText(EmailSignInActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                                mOutputText.setText("Password Incorrect");
                                mPasswordLayout.setError("Password Incorrect");
                            } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                mEmailLayout.setError("Email Incorrect");
                                Toast.makeText(EmailSignInActivity.this, "Email Incorrect", Toast.LENGTH_SHORT).show();
                                mOutputText.setText("Email Incorrect");
                            }

                        }
                        hideProgressBar();

                    }
                });

    }

    private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            mOutputText.setText("User not login");
            return;
        } else {
            mOutputText.setText(user.getEmail());
        }
    }

    private void createUser(View view) {

        if (!validateEmailAddress() | !validatePassword()) {
            // Email or Password not valid,
            return;
        }
        //Email and Password valid, create user here

        String email = mEmailLayout.getEditText().getText().toString().trim();
        String password = mPasswordLayout.getEditText().getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(EmailSignInActivity.this, "User Logged in", Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        if (e instanceof FirebaseAuthUserCollisionException) {
                            mEmailLayout.setError("Email already in use");
                            Toast.makeText(EmailSignInActivity.this, "Email already in use", Toast.LENGTH_SHORT).show();
                            mOutputText.setText("Email already in use");
                        }
                    }
                });

    }

    private void initViews() {
        mEmailLayout = findViewById(R.id.et_email);
        mPasswordLayout = findViewById(R.id.et_password);
        mBtnSignin = findViewById(R.id.btn_singin);
        mBtnRegisterUser = findViewById(R.id.btn_registeruser);
        mOutputText = findViewById(R.id.tv_output);
        mProgressBar = findViewById(R.id.progressbar);
        mBtnSignOut = findViewById(R.id.btn_singout);
    }

    private boolean validateEmailAddress() {

        String email = mEmailLayout.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            mEmailLayout.setError("Email is required. Can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailLayout.setError("Invalid Email. Enter valid email address.");
            return false;
        } else {
            mEmailLayout.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String password = mPasswordLayout.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            mPasswordLayout.setError("Password is required. Can't be empty.");
            return false;
        } else if (password.length() < 6) {
            mPasswordLayout.setError("Password length short. Minimum 6 characters required.");
            return false;
        } else {
            mPasswordLayout.setError(null);
            return true;
        }
    }

    private void showProgressBar() {
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
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
}