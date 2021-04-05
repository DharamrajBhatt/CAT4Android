package com.example.malabexam4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    private TextView email;
    private Button reset;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.reset_email);
        reset = findViewById(R.id.reset_btn);
        progress = findViewById(R.id.forgot_progress);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reset_email = email.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(reset_email).matches() || email.getText().toString().trim().isEmpty())
                {
                    email.setError("Enter a valid E-mail ID");
                    return;
                }

                progress.setVisibility(View.VISIBLE);


                FirebaseAuth.getInstance().sendPasswordResetEmail(reset_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgot_password.this, "Reset E-mail has been sent", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(forgot_password.this,loginscreen.class);
                                    startActivity(intent);
                                }
                            }
                        });

                progress.setVisibility(View.INVISIBLE);



            }
        });


    }
}