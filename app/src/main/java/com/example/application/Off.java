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

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Off extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TableView tableView;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off);

        tableView = findViewById(R.id.table);

        String [] header = {"Doctor Name","From day","To day"};
        String [][] data = { {"karam hattab","10/5/2022","20/5/2022"},{"Mohammad Mahdi","10/5/2022","20/5/2022"},{"Maria Mohammad","10/5/2022","20/5/2022"}
                ,{"Malik ahmad","10/5/2022","20/5/2022"},{"hala yousef","10/5/2022","20/5/2022"},{"ahmad karam","10/5/2022","20/5/2022"},{"diala ahmad","10/5/2022","20/5/2022"}
                ,{"jamal ali","10/5/2022","20/5/2022"},{"dina ali","10/5/2022","20/5/2022"},{"lara ali","10/5/2022","20/5/2022"},{"sara mohammad","10/5/2022","20/5/2022"}
                ,{"mahdi mohammad","10/5/2022","20/5/2022"},{"tala ahmad","10/5/2022","20/5/2022"},{"rawan ahmad","10/5/2022","20/5/2022"},{"sara ali","10/5/2022","20/5/2022"}};
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(Off.this,header));
        tableView.setDataAdapter(new SimpleTableDataAdapter(Off.this,data));


        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.fff);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Off.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

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
                Intent i2 = new Intent(Off.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(Off.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.home:
                Intent i1 = new Intent(Off.this,home.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(Off.this,profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(Off.this,lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(Off.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(Off.this,contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.Clinics:
                Intent i7 = new Intent(Off.this,Clinics2.class);
                startActivity(i7);
                finish();
                break;

            case R.id.feed:
                Intent i8 = new Intent(Off.this,feesback.class);
                startActivity(i8);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}