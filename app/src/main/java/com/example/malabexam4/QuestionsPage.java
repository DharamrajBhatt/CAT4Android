package com.example.malabexam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class QuestionsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);

        Intent intent = getIntent();
        String season = intent.getStringExtra("subject");

        if(season.equals("ml")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ml_fragment()).commit();
        }

        if(season.equals("we")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new we_fragment()).commit();
        }

        if(season.equals("ui")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ui_fragment()).commit();
        }

        if(season.equals("ma")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ma_fragment()).commit();
        }

    }
}