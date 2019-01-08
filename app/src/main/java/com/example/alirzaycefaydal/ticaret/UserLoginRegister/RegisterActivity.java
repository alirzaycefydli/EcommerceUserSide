package com.example.alirzaycefaydal.ticaret.UserLoginRegister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alirzaycefaydal.ticaret.HomeActivity;
import com.example.alirzaycefaydal.ticaret.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //widgets
    private ProgressBar progressBar;
    private EditText edit_name, edit_password, edit_mail;

    //vars
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        edit_name = findViewById(R.id.register_name);
        edit_password = findViewById(R.id.register_password);
        edit_mail = findViewById(R.id.register_mail);
        progressBar = findViewById(R.id.register_progress_bar);

        mRootRef = FirebaseDatabase.getInstance().getReference();
    }

    public void registerButtonClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        final String name = edit_name.getText().toString();
        String mail = edit_mail.getText().toString().trim();
        String password = edit_password.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.GONE);

            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);

                    if (task.isSuccessful()) {
                        String device_token = FirebaseInstanceId.getInstance().getToken();
                        String user_id = mAuth.getCurrentUser().getUid();

                        mRootRef.child("Users").child(user_id);

                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("name", name);
                        userMap.put("device_token", device_token);
                        userMap.put("user_id", user_id);
                        userMap.put("image", "default");

                        mRootRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Intent registerIntent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(registerIntent);
                                    finish();
                                }
                            }
                        });

                    }
                }
            });

        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, getString(R.string.fill_up), Toast.LENGTH_SHORT).show();
        }

    }


}
