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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Mobile extends AppCompatActivity {

    private Button b1;
    private EditText editText;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ProgressBar progressBar;
    Timer timer;
    int count = 0;
    mobiledata m;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    private FirebaseAuth auth1;
    private DatabaseReference reference;
    private String onlineUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        auth = FirebaseAuth.getInstance();
        auth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        editText = (EditText) findViewById(R.id.mobile);
        b1 = (Button) findViewById(R.id.k2);

        progressBar = (ProgressBar) findViewById(R.id.prog);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count++;
                progressBar.setProgress(count);
                if(count == 50){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,10,10);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh","My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        m = new mobiledata();
        auth = FirebaseAuth.getInstance();
        onlineUserId = auth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("patient").child(onlineUserId);

        b1.setOnClickListener(view -> {

            if (editText.getText().toString().trim().isEmpty()) {
                Toast.makeText(Mobile.this, "Enter Mobile", Toast.LENGTH_LONG).show();

            } else if (editText.getText().toString().trim().length() != 9) {
                Toast.makeText(Mobile.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
            } else {
                otpSend();
            }


            NotificationCompat.Builder builder = new NotificationCompat.Builder(Mobile.this,"myCh")
                    .setSmallIcon(android.R.drawable.stat_notify_sync)
                    .setContentTitle("Jordan University Hospital")
                    .setContentText("your Security Code is: 166200");

            notification = builder.build();
            notificationManagerCompat = NotificationManagerCompat.from(Mobile.this);
            notificationManagerCompat.notify(1,notification);


            final String mobile = editText.getText().toString();
            m.setMobile(mobile);

            reference.push().setValue(m);



        });
    }

    private void otpSend() {
        final String fn = editText.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        b1.setVisibility(View.INVISIBLE);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressBar.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                Toast.makeText(Mobile.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                progressBar.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                Toast.makeText(Mobile.this, "OTP is successfully send.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Mobile.this, Verification.class);
                intent.putExtra("verificationId", verificationId);
                startActivity(intent);
            }
        };

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber("+962" + editText.getText().toString().trim()).setTimeout(60L, TimeUnit.SECONDS).setActivity(this).setCallbacks(mCallbacks).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}