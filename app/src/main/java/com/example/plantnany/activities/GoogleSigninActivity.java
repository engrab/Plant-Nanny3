package com.example.plantnany.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantnany.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSigninActivity extends AppCompatActivity {

    public static final int SIGN_IN_REQUEST_CODE = 1001;
    public static final String TAG = "mytag";
    private GoogleSignInClient googleSignInClient;
    TextView mOutput;
    Button signOut;
    private SignInButton googleSignBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_signin);
        mOutput = findViewById(R.id.tv_out_put);
        signOut = findViewById(R.id.btn_sign_out);
        googleSignBtn = findViewById(R.id.google_sign_in);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        updateUI(mAuth.getCurrentUser());

        googleSignBtn.setOnClickListener(this::signIn);
        signOut.setOnClickListener(this::signOut);

    }

    private void signOut(View view) {

        mAuth.signOut();
        updateUI(mAuth.getCurrentUser());
    }

    private void signIn(View view) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
    }

    private void updateUI(FirebaseUser firebaseUser) {

        if (firebaseUser != null) {
            signOut.setVisibility(View.VISIBLE);
            mOutput.setText(firebaseUser.getDisplayName() + " \n" + firebaseUser.getEmail());
            googleSignBtn.setVisibility(View.GONE);
        } else {
            signOut.setVisibility(View.GONE);
            mOutput.setText("User is not Logged in");
            googleSignBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {

            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignIn(accountTask);
        }
    }

    private void handleGoogleSignIn(Task<GoogleSignInAccount> accountTask) {


        try {
            GoogleSignInAccount result = accountTask.getResult(ApiException.class);

            firebaseAuthWithGoogle(result);

        } catch (ApiException e) {
            mOutput.setText(GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode()));
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
                            Toast.makeText(GoogleSigninActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(GoogleSigninActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}