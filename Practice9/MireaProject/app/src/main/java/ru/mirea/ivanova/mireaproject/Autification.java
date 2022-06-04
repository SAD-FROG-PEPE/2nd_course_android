package ru.mirea.ivanova.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Autification extends AppCompatActivity implements View.OnClickListener {

    private EditText emailField;
    private EditText passwordField;
    private TextView statusTextView;
    private FirebaseAuth auth;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autification);
        emailField = findViewById(R.id.fieldEmail);
        passwordField = findViewById(R.id.fieldPassword);
        statusTextView = findViewById(R.id.status);
        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        findViewById(R.id.signOutButton).setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            findViewById(R.id.fieldEmail).setVisibility(View.GONE);
            findViewById(R.id.fieldPassword).setVisibility(View.GONE);
            findViewById(R.id.emailSignInButton).setVisibility(View.GONE);
            findViewById(R.id.emailCreateAccountButton).setVisibility(View.GONE);
            findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
        } else {
            statusTextView.setText(R.string.signed_out);
            findViewById(R.id.fieldPassword).setVisibility(View.VISIBLE);
            findViewById(R.id.fieldEmail).setVisibility(View.VISIBLE);
            findViewById(R.id.emailSignInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.emailCreateAccountButton).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }
        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = auth.getCurrentUser();
                updateUI(user);
                finish();
                openMain();
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(Autification.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                    finish();
                    openMain();
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(Autification.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
                if (!task.isSuccessful()) {
                    statusTextView.setText(R.string.auth_failed);
                    finish();
                }
            }
        });
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void signOut() {
        auth.signOut();
        updateUI(null);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.emailSignInButton) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.signOutButton) {
            signOut();
        }
    }
}