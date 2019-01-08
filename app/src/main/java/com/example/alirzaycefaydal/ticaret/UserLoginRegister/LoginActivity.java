package com.example.alirzaycefaydal.ticaret.UserLoginRegister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alirzaycefaydal.ticaret.HomeActivity;
import com.example.alirzaycefaydal.ticaret.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //widgets
    private EditText edit_mail, edit_password;
    private ProgressBar progressBar;
    private TextView forget_password_text;
    private CheckBox remember_me_box;


    //vars
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.login_progress_bar);
        edit_mail = findViewById(R.id.login_mail);
        edit_password = findViewById(R.id.login_password);
        forget_password_text = findViewById(R.id.login_forget_password);
        remember_me_box = findViewById(R.id.login_remember_me);
    }

    public void loginButtonClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String mail = edit_mail.getText().toString();
        String password = edit_password.getText().toString();

        if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.INVISIBLE);

            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);
                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.fill_up), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser current_user = mAuth.getCurrentUser();

        if (current_user != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }
}
