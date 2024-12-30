package com.example.vikaapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        TextView greeting = findViewById(R.id.userGreeting);

        // Retrieve username from intent
        String username = getIntent().getStringExtra("username");
        greeting.setText("Xin chào, " + username + "!");
    }
}
