package com.example.application;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ret extends AppCompatActivity {

    private TextView result, date;
    private Button button;
    private DrawerLayout drawerLayout;
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth auth;
    private Uri resultUri;
    private String date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ret);

         date =  findViewById(R.id.dateTv);
         button =  findViewById(R.id.button3);
        result = findViewById(R.id.result);

        Bundle b = getIntent().getExtras();
         date1 = b.getString("date");
        date.setText(date1);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("Lab").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String finalResult = "No results found in this date..";
                if(snapshot.exists()){
                    finalResult = "";
                    for (int i = 0; i< snapshot.getChildrenCount(); i++){
                        DataSnapshot dataSnapshot = snapshot.child("" + i);
                        if (dataSnapshot.getValue() != null){
                            String result= (String) dataSnapshot.child("test_result").getValue();
                            String date = (String) dataSnapshot.child("date").getValue();
                            if (date.equals(date1)) {
                                finalResult = "Your test result in " + date1 + " is: " + result;
                                break;
                            }
                        }
                    }
                }
                result.setText(finalResult);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ret.this, Lab.class);
                startActivity(intent);
            }
        });
    }
    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        mReference = FirebaseDatabase.getInstance().getReference("Register");

        mReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}