package com.example.malabexam4;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class sign_up extends AppCompatActivity {

    private EditText customer_name;
    private EditText customer_email;
    private EditText customer_phone;
    private EditText customer_address;
    private EditText customer_pass;
    private EditText customer_pass2;
    private Button sign_up;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference cusref;
    private boolean flag = false;
    customer Customer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        customer_name = (EditText) findViewById(R.id.cust_name);
        customer_email = (EditText) findViewById(R.id.cust_email);
        customer_phone = (EditText) findViewById(R.id.cust_phone);
        customer_address = (EditText) findViewById(R.id.cust_address);
        customer_pass = (EditText) findViewById(R.id.cust_pass);
        customer_pass2 = (EditText) findViewById(R.id.cust_pass2);
        sign_up = (Button) findViewById(R.id.reg_button);
        mAuth = FirebaseAuth.getInstance();


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = customer_name.getText().toString().trim();
                final String email = customer_email.getText().toString().trim();
                final String phone = customer_phone.getText().toString().trim();
                String register = customer_address.getText().toString().trim();
                final String password = customer_pass.getText().toString().trim();
                final String password2 = customer_pass2.getText().toString().trim();
                database = FirebaseDatabase.getInstance();
                cusref = database.getReference("Customer");
                Customer = new customer();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(register) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {

                    customer_pass.setError("The password should have atleast 6 characters");
                    return;
                }


                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    customer_email.setError("Enter a valid E-mail ID");
                    return;
                }

                if(phone.length() != 10){
                    customer_phone.setError("Enter a valid phone number");
                    return;
                }

                cusref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            customer Customer = postSnapshot.getValue(customer.class);
                            if(Customer.getCust_phone().equals(customer_phone.getText().toString().trim())){
                                Toast.makeText(getApplicationContext(), "This phone number already exits", Toast.LENGTH_SHORT).show();
                                flag=true;
                            }
                        }

                        if (password.equals(password2) & !flag) {

                            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        getValue();
                                        String id = cusref.push().getKey();
                                        cusref.child(id).setValue(Customer);
                                        Toast.makeText(sign_up.this, "Your account has been successfully created", Toast.LENGTH_LONG).show();
                                        cleartext();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(sign_up.this, "This email already exits", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                    mAuth.signOut();

                                }
                            });



                            return;


                        }
                        else if (!password.equals(password2)){
                            customer_pass.setError("The passwords do not match");
                            customer_pass2.setError("The passwords do not match");
                            return;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }


        });



    }

    private void getValue()
    {
        Customer.setCust_name(customer_name.getText().toString().trim());
        Customer.setCust_email(customer_email.getText().toString().trim());
        Customer.setCust_phone(customer_phone.getText().toString().trim());
        Customer.setCust_register(customer_address.getText().toString().trim());
        Customer.setCust_password(customer_pass.getText().toString().trim());
    }

    private void cleartext()
    {
        customer_name.setText("");
        customer_email.setText("");
        customer_phone.setText("");
        customer_address.setText("");
        customer_pass.setText("");
        customer_pass2.setText("");
    }
}

