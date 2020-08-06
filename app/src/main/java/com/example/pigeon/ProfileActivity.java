package com.example.pigeon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=getIntent().getStringExtra("value");
        TextView ptv=(TextView)findViewById(R.id.profile_tv);
        ptv.setText(name);
    }
}