package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verification extends AppCompatActivity {

    private EditText edit1,edit2,edit3,edit4,edit5,edit6;
    private TextView textView;
    private String verificationId;
    private ProgressBar progressBar;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        edit1 = findViewById(R.id.code1);
        edit2 = findViewById(R.id.code2);
        edit3 = findViewById(R.id.code3);
        edit4 = findViewById(R.id.code4);
        edit5 = findViewById(R.id.code5);
        edit6 = findViewById(R.id.code6);
        textView = findViewById(R.id.textView6);

        progressBar = (ProgressBar) findViewById(R.id.prog);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh","My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Verification.this,"myCh")
                        .setSmallIcon(android.R.drawable.stat_notify_sync)
                        .setContentTitle("Jordan University Hospital")
                        .setContentText("your Security Code is: 616200");

                notification = builder.build();
                notificationManagerCompat = NotificationManagerCompat.from(Verification.this);
                notificationManagerCompat.notify(1,notification);
            }
        });

        setupOTPInputs();

        final Button buttonV = findViewById(R.id.k3);
        verificationId = getIntent().getStringExtra("verificationId");

        buttonV.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (edit1.getText().toString().trim().isEmpty()
                    || edit2.getText().toString().trim().isEmpty()
                        || edit3.getText().toString().trim().isEmpty()
                        || edit4.getText().toString().trim().isEmpty()
                        || edit5.getText().toString().trim().isEmpty()
                        || edit6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Verification.this, "Please enter valid code", Toast.LENGTH_LONG).show();
                    return;
                }
                String code = edit1.getText().toString() +
                        edit2.getText().toString() +
                        edit3.getText().toString() +
                        edit4.getText().toString() +
                        edit5.getText().toString() +
                        edit6.getText().toString();

                if (verificationId != null){
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Verification.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Toast.makeText(Verification.this, "Your account has been created successfully.", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }else{
                                Toast.makeText(Verification.this, "The Verification code entered was invalid", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }

            }

        });
    }

    private void setupOTPInputs(){
        edit1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    edit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    edit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    edit4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    edit5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    edit6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
