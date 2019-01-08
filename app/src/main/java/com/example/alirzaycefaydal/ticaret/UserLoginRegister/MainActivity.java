package com.example.alirzaycefaydal.ticaret.UserLoginRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.alirzaycefaydal.ticaret.HomeActivity;
import com.example.alirzaycefaydal.ticaret.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String current_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
    }

    public void registerButtonClicked(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    public void loginButtonClicked(View view) {
        Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (current_user_id != null) {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }

    }
}
