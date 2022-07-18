package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


    String[] item1 = {"Male", "Female"};
    Button b;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    private EditText textView1, textView2, textView3, textView4, textView5;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textView1 = (EditText) findViewById(R.id.FN);
        textView2 = (EditText) findViewById(R.id.LN);
        textView3 = (EditText) findViewById(R.id.NO);
        textView4 = (EditText) findViewById(R.id.Password);
        textView5 = (EditText) findViewById(R.id.date);
        autoCompleteTextView = findViewById(R.id.gender);
        database = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("patient");

        b = (Button) findViewById(R.id.next);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data from edit text
                final String fn = textView1.getText().toString().trim();
                final String ln = textView2.getText().toString().trim();
                final String NN = textView3.getText().toString().trim();
                final String pass = textView4.getText().toString().trim();
                final String date = textView5.getText().toString().trim();
                final String gender = autoCompleteTextView.getText().toString();

                // check of user fill all the field
                if (TextUtils.isEmpty(fn)) {
                    textView1.setError("First Name is required");
                    return;
                }
                if (TextUtils.isEmpty(ln)) {
                    textView2.setError("Last Name is required");
                    return;
                }
                if (TextUtils.isEmpty(NN)) {
                    textView3.setError("National Number is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    textView4.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    textView5.setError("Date is required");
                    return;
                }
                if (TextUtils.isEmpty(gender)) {
                    autoCompleteTextView.setError("Gender is required");
                    return;
                }


                auth.createUserWithEmailAndPassword(NN, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            firebaseUser = task.getResult().getUser();
                            DatabaseReference newUser = reference.child(firebaseUser.getUid());
                            newUser.child("First Name").setValue(fn);
                            newUser.child("Last Name").setValue(ln);
                            newUser.child("Email").setValue(NN);
                            newUser.child("Password").setValue(pass);
                            newUser.child("Gender").setValue(gender);
                            newUser.child("Date").setValue(date);

                            Intent intent = new Intent(SignUpActivity.this, Mobile.class);
                            startActivity(intent);

                            Toast.makeText(SignUpActivity.this, "Successful Registration", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }


        });

        adapter = new ArrayAdapter(this, R.layout.activity_list, item1);
        autoCompleteTextView.setAdapter(adapter);

    }


}




