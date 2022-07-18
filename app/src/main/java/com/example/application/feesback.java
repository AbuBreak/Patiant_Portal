package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feesback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button button;
    private EditText editText;
    private AutoCompleteTextView autoCompleteTextView;

    String [] category = {"Complaint","Suggestion","Gratitude","Notes"};

    ArrayAdapter<String> adapter1;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;
    Dialog dialog;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private String onlineUserId = "";

    private ProgressBar progressBar;

    feed f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feesback);

        toolbar1 = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.feed);
        new_view = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("JUH Application");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(feesback.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        new_view.setNavigationItemSelectedListener(this);

        autoCompleteTextView = findViewById(R.id.cat);
        button = findViewById(R.id.sent);
        editText = findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        adapter1 = new ArrayAdapter<String>(this,R.layout.activity_list,category);


        autoCompleteTextView.setAdapter(adapter1);


        dialog = new Dialog(this);

        f = new feed();
        auth = FirebaseAuth.getInstance();
        onlineUserId = auth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("Feedback").child(onlineUserId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);
                final String c = autoCompleteTextView.getText().toString();

                final String con = editText.getText().toString();

                if (TextUtils.isEmpty(c)){
                    autoCompleteTextView.setError("Category is required");
                    return;
                }

                if (TextUtils.isEmpty(con)){
                    editText.setError("Content is required");
                    return;
                }



                f.setCategory(c);

                f.setContent(con);

                reference.push().setValue(f);

                openDialog();



            }
        });
    }

    private void openDialog() {
        dialog.setContentView(R.layout.activity_suc_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));



        Button close = dialog.findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                progressBar.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
                autoCompleteTextView.getText().clear();
                editText.getText().clear();
            }
        });

        dialog.show();
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
                Intent i2 = new Intent(feesback.this,Insurance.class);
                startActivity(i2);
                finish();
                break;

            case R.id.Log_Out:
                Intent i = new Intent(feesback.this,MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.home:
                Intent i1 = new Intent(feesback.this,home.class);
                startActivity(i1);
                finish();
                break;

            case R.id.Profile:
                Intent i3 = new Intent(feesback.this,profile_patient.class);
                startActivity(i3);
                finish();
                break;

            case R.id.LabTest:
                Intent i4 = new Intent(feesback.this,lab_test_info.class);
                startActivity(i4);
                finish();
                break;

            case R.id.result:
                Intent i5 = new Intent(feesback.this,Lab.class);
                startActivity(i5);
                finish();
                break;

            case R.id.us:
                Intent i6 = new Intent(feesback.this,contact_us.class);
                startActivity(i6);
                finish();
                break;

            case R.id.Clinics:
                Intent i7 = new Intent(feesback.this,Clinics2.class);
                startActivity(i7);
                finish();
                break;

            case R.id.off:
                Intent i9 = new Intent(feesback.this,Off.class);
                startActivity(i9);
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}