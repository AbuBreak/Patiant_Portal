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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class lab_test_info extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AutoCompleteTextView txtSearch;
    TextView testId, testType, testDesc, testFee;
    String[] id = {"1", "2", "3", "4"};
    String[] types = {"B12", "BLOOD_PRESSURE", "ZINC", "BFL"};
    String[] desc = {"Vitamin B12 is an essential water-soluble vitamin that is commonly found in a variety of foods such as fish, shellfish, meat, and dairy products.",
            "Blood pressure is the term used to describe the strength with which your blood pushes on the sides of your arteries as it's pumped around your body.",
            "Zinc (Zn) is contained mainly in bones, teeth, hair, skin, liver, muscle, leukocytes, and testes. Zinc is a component of several hundred enzymes",
            "In body fluid analysis, it is important to determine whether the fluid is a transudate or an exudate because it can help diagnose the disease or condition present."};
    String[] fee = {"25 JD", "10 JD", "50 JD", "45 JD"};

    ArrayAdapter<String> idAdapter, typesAdapter, descAdapter, feeAdapter;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_info);

        txtSearch = findViewById(R.id.txtSearch);
        initViews();
        initAdapters();
        txtSearch.setAdapter(typesAdapter);


      /*  lab_info= new Lab_Info();
        labAuth= FirebaseAuth.getInstance();
        OnlineUserId= labAuth.getCurrentUser().getUid();

        labReference= FirebaseDatabase.getInstance().getReference().child("LabTestInfo").child(OnlineUserId);*/

        txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                testId.setText(idAdapter.getItem(i));
                testType.setText(typesAdapter.getItem(i));
                testDesc.setText(descAdapter.getItem(i));
                testFee.setText(feeAdapter.getItem(i));
            }
        });

        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.ins);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(lab_test_info.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);

    }

    private void initAdapters() {
        idAdapter = new ArrayAdapter<>(lab_test_info.this, android.R.layout.simple_list_item_1, id);
        descAdapter = new ArrayAdapter<>(lab_test_info.this, android.R.layout.simple_list_item_1, desc);
        feeAdapter = new ArrayAdapter<>(lab_test_info.this, android.R.layout.simple_list_item_1, fee);
        typesAdapter = new ArrayAdapter<>(lab_test_info.this, android.R.layout.simple_dropdown_item_1line, types);
    }

    private void initViews() {
        testId = (TextView) findViewById(R.id.testId);
        testType = (TextView) findViewById(R.id.testType);
        testDesc = (TextView) findViewById(R.id.testDesc);
        testFee = (TextView) findViewById(R.id.testFee);
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
                Intent i2 = new Intent(lab_test_info.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(lab_test_info.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.Clinics:
                Intent i1 = new Intent(lab_test_info.this,Clinics2.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(lab_test_info.this,profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.home:
                Intent i4 = new Intent(lab_test_info.this,home.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(lab_test_info.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(lab_test_info.this,contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(lab_test_info.this,feesback.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(lab_test_info.this,Off.class);
                startActivity(i9);
                finish();
                break;




            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
