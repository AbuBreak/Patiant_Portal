package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lab extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList itemsList = new ArrayList<String>();

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    private Button button;
    private TextView textView;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;


    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private Boolean mFinishGetData = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        autoCompleteTextView = findViewById(R.id.date);
        button = findViewById(R.id.button2);


        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.Lab);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Lab.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);

        adapter = new ArrayAdapter(this, R.layout.activity_list, itemsList);
        autoCompleteTextView.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("Lab").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Log.d("abeer", Lab.class.getName() + " : " + FirebaseAuth.getInstance().getCurrentUser().getUid());

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                        DataSnapshot dataSnapshot = snapshot.child("" + i);
                        if (dataSnapshot.getValue() != null) {
                            itemsList.add(dataSnapshot.child("date").getValue());
                        }
                    }
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void buClic(View view) {

        Intent intent = new Intent(Lab.this, ret.class);
        Bundle b = new Bundle();
        b.putString("date", autoCompleteTextView.getText().toString());
        intent.putExtras(b);

        startActivity(intent);
    }

    public boolean onCreateOptionMenu(Menu menu) {
        MenuInflater ObjMenuInflater = getMenuInflater();
        ObjMenuInflater.inflate(R.menu.main_mune, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Insurance:
                Intent i2 = new Intent(Lab.this, Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(Lab.this, MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.Clinics:
                Intent i1 = new Intent(Lab.this, Clinics2.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(Lab.this, profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(Lab.this, lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.home:
                Intent i5 = new Intent(Lab.this, home.class);
                startActivity(i5);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(Lab.this, contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(Lab.this, feesback.class);
                startActivity(i7);
                finish();
                break;
            case R.id.off:
                Intent i9 = new Intent(Lab.this,Off.class);
                startActivity(i9);
                finish();
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}