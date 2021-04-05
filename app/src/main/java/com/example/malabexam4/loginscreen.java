package com.example.malabexam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginscreen extends AppCompatActivity {


    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private Button sign_up_button;
    private TextView forgot;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);


        mAuth = FirebaseAuth.getInstance();
        forgot = findViewById(R.id.forgot_password_text);

        loginEmailText = (EditText) findViewById(R.id.stud_login_email);
        loginPassText = (EditText) findViewById(R.id.stud_login_password);
        loginBtn = (Button) findViewById(R.id.stud_login_btn);
        progressBar = (ProgressBar) findViewById(R.id.login_progress) ;
        sign_up_button = (Button) findViewById(R.id.sign_up);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String loginEmail = loginEmailText.getText().toString().trim();
                String loginPass = loginPassText.getText().toString().trim();

                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){

                    progressBar.setVisibility(View.VISIBLE);


                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                sendtomain();

                            }
                            else{

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(loginscreen.this, "Error :" +errorMessage,Toast.LENGTH_LONG).show();

                            }

                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    });

                }



            }
        });



        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(loginscreen.this,sign_up.class);
                startActivity(intent);

            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(loginscreen.this,forgot_password.class);
                startActivity(intent);

            }
        });



    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            sendtomain();

        }

    }

    private void sendtomain() {

        Intent mainintent = new Intent(loginscreen.this,Subject_Page.class);
        startActivity(mainintent);
        finish();
    }

}