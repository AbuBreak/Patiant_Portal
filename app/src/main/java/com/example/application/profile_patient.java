package com.example.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class profile_patient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mainnameTxtview, nameTxtview, phoneTxtview;
    private TextView nationalTxtview, genderTxtview, birthdayTxtview;
    private ImageView userImageview, nameImageview, phoneImageview,img;
    private ImageView nationalImageview, genderImageview, birthdayImageview;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;
    private String email,password;
    private Button button;

    private DatabaseReference reference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private Uri resultUri;





    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_patient);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        mainnameTxtview = findViewById(R.id.mainname_textview);
        nameTxtview = findViewById(R.id.name_textview);
        phoneTxtview = findViewById(R.id.phone_textview);
        nationalTxtview = findViewById(R.id.national_textview);
        genderTxtview = findViewById(R.id.gender_textview);
        birthdayTxtview = findViewById(R.id.birthday_textview);
        userImageview = findViewById(R.id.user_imageview);


        nationalImageview = findViewById(R.id.national_imageview);
        genderImageview = findViewById(R.id.gender_imageview);
        birthdayImageview = findViewById(R.id.birthday_imageview);
        button = findViewById(R.id.open);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(m, 200);
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("patient").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String name = snapshot.child("First Name").getValue().toString() + " " + snapshot.child("Last Name").getValue().toString() ;
                    nameTxtview.setText(name);

                    String NN = snapshot.child("Email").getValue().toString();
                    nationalTxtview.setText(NN);

                    String gender = snapshot.child("Gender").getValue().toString();
                    genderTxtview.setText(gender);

                    String date = snapshot.child("Date").getValue().toString();
                    birthdayTxtview.setText(date);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.pat);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(profile_patient.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Register");

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && requestCode == RESULT_OK && data != null){
            resultUri = data.getData();
            userImageview.setImageURI(resultUri);
        }
    }

    public boolean onCreateOptionMenu(Menu menu) {
        MenuInflater ObjMenuInflater = getMenuInflater();
        ObjMenuInflater.inflate(R.menu.main_mune, menu);

        return super.onCreateOptionsMenu(menu);
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Insurance:
                Intent i2 = new Intent(profile_patient.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(profile_patient.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.Clinics:
                Intent i1 = new Intent(profile_patient.this,Clinics2.class);
                startActivity(i1);
                finish();
                break;

            case R.id.us:
                Intent i3 = new Intent(profile_patient.this,contact_us.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(profile_patient.this,lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(profile_patient.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.home:
                Intent i6 = new Intent(profile_patient.this,home.class);
                startActivity(i6);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(profile_patient.this,feesback.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(profile_patient.this,Off.class);
                startActivity(i9);
                finish();
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}