package com.example.plantnany.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.plantnany.R;
import com.example.plantnany.databinding.ActivitySignInBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 61;
    private ActivitySignInBinding binding;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "SignInActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;


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
        binding.btnGoogle.setOnClickListener(this::googleSingIn);
        binding.btnFacebook.setOnClickListener(this::facebookSignIn);

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

    private void googleSingIn(View view) {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignIn.getLastSignedInAccount(this);

        signIn();

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
    }

    private void updateUI(FirebaseUser firebaseUser) {

        if (firebaseUser != null) {
            binding.tvEmail1.setText(firebaseUser.getEmail());
            Toast.makeText(this, "User Login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User not Login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignIn(task);
        }
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void handleGoogleSignIn(Task<GoogleSignInAccount> accountTask) {


        try {
            GoogleSignInAccount result = accountTask.getResult(ApiException.class);

            firebaseAuthWithGoogle(result);

        } catch (ApiException e) {
            Toast.makeText(this, "" + GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode()), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "handleGoogleSignIn: Error status code: " + e.getStatusCode());
            Log.d(TAG, "handleGoogleSignIn: Error status message: "
                    + GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode()));
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount result) {

        AuthCredential credential = GoogleAuthProvider.getCredential(result.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String s = user.getEmail().toString();
                            binding.tvEmail1.setText(s);
                            updateUI(user);
                        } else {
                            Toast.makeText(SignInActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void facebookSignIn(View view) {


        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Arrays.asList("email", "public_profile"));

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignInActivity.this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        updateUI();
                    }
                });
    }
}