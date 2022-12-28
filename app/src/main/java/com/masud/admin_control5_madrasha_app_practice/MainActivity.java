package com.masud.admin_control5_madrasha_app_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    MaterialCardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = findViewById(R.id.noticeViewId);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNoticeViewId = new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(goToNoticeViewId);
            }
        });
    }
}