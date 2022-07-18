package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton next,before;
    private ImageSwitcher imageSwitcher;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;

    int index = 0;
    int gorseller [] = {R.drawable.slide_one,R.drawable.slide_two,R.drawable.slide_three,R.drawable.slide_four};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.homee);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(home.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);


        before = findViewById(R.id.before);
        next = findViewById(R.id.next);


        imageSwitcher = findViewById(R.id.switchh);

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --index;
                if (index<0){
                    index = gorseller.length-1;
                }

                imageSwitcher.setImageResource(gorseller[index]);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;

                if(index == gorseller.length){
                    index=0;
                }
                imageSwitcher.setImageResource(gorseller[index]);
            }
        });

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setMaxWidth(250);
                imageView.setMaxHeight(250);
                return imageView;
            }
        });

        imageSwitcher.setImageResource(gorseller[index]);

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
                Intent i2 = new Intent(home.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(home.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(home.this,contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.Clinics:
                Intent i1 = new Intent(home.this,Clinics2.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(home.this,profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(home.this,lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(home.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(home.this,feesback.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(home.this,Off.class);
                startActivity(i9);
                finish();
                break;





            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}