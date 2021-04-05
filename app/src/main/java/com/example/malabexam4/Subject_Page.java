package com.example.malabexam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Subject_Page extends AppCompatActivity {

    private Spinner subject;
    private Button subject_next;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__page);

        subject = findViewById(R.id.subject_spinner);
        subject_next = findViewById(R.id.subject_button);

        subject_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sub = subject.getSelectedItem().toString();

                if(sub.equals("Machine Learning")){
                    Intent intent = new Intent(Subject_Page.this,QuestionsPage.class);
                    intent.putExtra("subject","ml");
                    startActivity(intent);
                }

                if(sub.equals("Web Technologies")){
                    Intent intent = new Intent(Subject_Page.this,QuestionsPage.class);
                    intent.putExtra("subject","we");
                    startActivity(intent);
                }

                if(sub.equals("Mobile Applications")){
                    Intent intent = new Intent(Subject_Page.this,QuestionsPage.class);
                    intent.putExtra("subject","ma");
                    startActivity(intent);
                }

                if(sub.equals("UI")){
                    Intent intent = new Intent(Subject_Page.this,QuestionsPage.class);
                    intent.putExtra("subject","ui");
                    startActivity(intent);
                }

            }
        });


    }
}