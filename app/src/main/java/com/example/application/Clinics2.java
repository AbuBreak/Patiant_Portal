package com.example.application;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import java.util.Random;

public class Clinics2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String[] Department = {"General surgery ", "Ear, Nose and Throat", "dermatology ", "Eye", "Dental","Urinary Tract","heart", "Neurosurgery"};
    String[] itemD1 = {"Ahmad Mohammad", "Mahdi aljammal","Diala ahmad","leen hani","jamal ahmad"};
    String[] itemD2 = {"Malik abubreak", "Yousef ali", "hamzah zain","omar ahmad"};
    String[] itemD3 = {"Moahammad mahdi", "Hala hani","tala moahammad","reem anas","wael ali","hamzah mohammad"};
    String[] itemD4 = {"Karam hattab", "Maria mohammad","rawan ahmad","kareem adel","zakaria ahmad"};
    String[] itemD5 = {"Amal ahmad", "Zaina hani","dina hani"};
    String[] itemD6 = {"nawal ali", "ahmad karam","farah ali","lina kamal"};
    String[] itemD7 = {"roaa wael", "suzan mohammad","mahdi omar","mohammad ahmad"};
    String[] itemD8 = {"sameer anas", "basel abdullah","abdullah nadeem","yazan hadi"};
    String[] item3 = {"5/6/2022", "6/6/2022", "7/6/2022", "8/6/2022", "9/6/2022", "12/6/2022"};
    String[] item4 = {"8-9 AM", "9-10 AM", "10-11 AM", "11-12 AM", "1-2 PM", "2-3 PM"};
    String[] item5 = {"Student", "JU Employee", "Ministry of Health", "Nothing"};

    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2, autoCompleteTextView3, autoCompleteTextView4, autoCompleteTextView5;
    ArrayAdapter<String> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8, adapter9,adapter10,adapter11,adapter12;
    private Button button;
    private TextView textView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private String onlineUserId = "";

    Appointment appointment;
    private String department, name, date, hour, insurance;
    public Dialog mDialog;
    Boolean mIsValid = true;
    private ValueEventListener mValueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics2);

        mDialog = new Dialog(Clinics2.this);
        autoCompleteTextView = findViewById(R.id.dep);
        autoCompleteTextView2 = findViewById(R.id.doc);
        autoCompleteTextView3 = findViewById(R.id.date);
        autoCompleteTextView4 = findViewById(R.id.hour);
        autoCompleteTextView5 = findViewById(R.id.ins);
        button = findViewById(R.id.buttonAppointmentBook);
        textView = findViewById(R.id.textView14);

        adapter = new ArrayAdapter(this, R.layout.activity_list, Department);
        adapter2 = new ArrayAdapter(this, R.layout.activity_list, itemD1);
        adapter3 = new ArrayAdapter(this, R.layout.activity_list, itemD2);
        adapter4 = new ArrayAdapter(this, R.layout.activity_list, itemD3);
        adapter5 = new ArrayAdapter(this, R.layout.activity_list, itemD4);
        adapter8 = new ArrayAdapter(this, R.layout.activity_list, itemD5);
        adapter10 = new ArrayAdapter(this, R.layout.activity_list, itemD6);
        adapter11 = new ArrayAdapter(this, R.layout.activity_list, itemD7);
        adapter12 = new ArrayAdapter(this, R.layout.activity_list, itemD8);


        adapter6 = new ArrayAdapter(this, R.layout.activity_list, item3);
        adapter7 = new ArrayAdapter(this, R.layout.activity_list, item4);
        adapter9 = new ArrayAdapter(this, R.layout.activity_list, item5);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        autoCompleteTextView2.setAdapter(adapter2);
                        break;

                    case 1:
                        autoCompleteTextView2.setAdapter(adapter3);
                        break;

                    case 2:
                        autoCompleteTextView2.setAdapter(adapter4);
                        break;

                    case 3:
                        autoCompleteTextView2.setAdapter(adapter5);
                        break;

                    case 4:
                        autoCompleteTextView2.setAdapter(adapter8);
                        break;

                    case 5:
                        autoCompleteTextView2.setAdapter(adapter10);
                        break;
                    case 6:
                        autoCompleteTextView2.setAdapter(adapter11);
                        break;
                    case 7:
                        autoCompleteTextView2.setAdapter(adapter12);
                        break;
                }
            }
        });

        autoCompleteTextView3.setAdapter(adapter6);
        autoCompleteTextView4.setAdapter(adapter7);
        autoCompleteTextView5.setAdapter(adapter9);

        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.Clinics);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Clinics2.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);

        appointment = new Appointment();

        auth = FirebaseAuth.getInstance();
        onlineUserId = auth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("Appointment");
        Log.d("abeer", Clinics2.class.getName() + " : " + FirebaseAuth.getInstance().getCurrentUser().getUid());

        button.setOnClickListener(view -> {

            department = autoCompleteTextView.getText().toString();
            name = autoCompleteTextView2.getText().toString();
            date = autoCompleteTextView3.getText().toString();
            hour = autoCompleteTextView4.getText().toString();
            insurance = autoCompleteTextView5.getText().toString();


            if (TextUtils.isEmpty(department)) {
                autoCompleteTextView.setError("Department is required");
                return;
            }
            if (TextUtils.isEmpty(name)) {
                autoCompleteTextView2.setError("Doctor Name is required");
                return;
            }
            if (TextUtils.isEmpty(date)) {
                autoCompleteTextView3.setError("Date is required");
                return;
            }
            if (TextUtils.isEmpty(hour)) {
                autoCompleteTextView4.setError("Hour is required");
                return;
            }
            if (TextUtils.isEmpty(insurance)) {
                autoCompleteTextView5.setError("Insurance Type is required");
                return;
            }

            appointment.setDepartment(department);
            appointment.setName(name);
            appointment.setDate(date);
            appointment.setHour(hour);
            appointment.setInsurance(insurance);


            mIsValid = true;
            checkIfAvailableAppointment();

        });

    }

    private void checkIfAvailableAppointment() {
        mValueEventListener = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    Iterable<DataSnapshot> data = snapshot.getChildren();
                    data.forEach(parentChild -> { //b10vb3DLzQUW2hBaflwl7UJ191l1
                        parentChild.getChildren().forEach(branches -> { // -N1NwhbzF3HdzGPyw-Hu, -N27U6yoONCnheNh78f8
                            if (branches.child("date").getValue().equals(date)
                                    && branches.child("hour").getValue().equals(hour)
                                    && branches.child("name").getValue().equals(name)) {
                                showErrorDialog();
                                mIsValid = false;
                                return;
                            }
                        });
                    });

                    if (mIsValid) {
                        String childId = "sdgeAOJk42" + new Random().nextInt(18);
                        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(childId).setValue(appointment);
                        reference.removeEventListener(mValueEventListener);
                        showSuccessDialog();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addValueEventListener(mValueEventListener);



    }

    private void showErrorDialog() {
        mDialog.setContentView(R.layout.appointement_error_dialog);
        Window window = mDialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button done = mDialog.findViewById(R.id.buttonAppointmentDialogDone);

        done.setOnClickListener(v -> mDialog.dismiss());

        mDialog.show();
    }

    private void showSuccessDialog() {
        mDialog.setContentView(R.layout.appointement_successful_dialog);
        Window window = mDialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button done = mDialog.findViewById(R.id.buttonAppointmentDialogDone);

        done.setOnClickListener(v -> mDialog.dismiss());

        mDialog.show();
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
                Intent i2 = new Intent(Clinics2.this, Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(Clinics2.this, MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.home:
                Intent i1 = new Intent(Clinics2.this, home.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(Clinics2.this, profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(Clinics2.this, lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(Clinics2.this, Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(Clinics2.this, contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.feedback:
                Intent i7 = new Intent(Clinics2.this, feesback.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(Clinics2.this,Off.class);
                startActivity(i9);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}