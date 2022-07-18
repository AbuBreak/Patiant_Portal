package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class contact_us extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.usss);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(contact_us.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);


    }

    public boolean onCreateOptionMenu(Menu menu) {
        MenuInflater ObjMenuInflater = getMenuInflater();
        ObjMenuInflater.inflate(R.menu.main_mune,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Insurance:
                Intent i2 = new Intent(contact_us.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(contact_us.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.Clinics:
                Intent i1 = new Intent(contact_us.this,Clinics2.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(contact_us.this,profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(contact_us.this,lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(contact_us.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.home:
                Intent i6 = new Intent(contact_us.this,home.class);
                startActivity(i6);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(contact_us.this,feesback.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(contact_us.this,Off.class);
                startActivity(i9);
                finish();
                break;






            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    }


