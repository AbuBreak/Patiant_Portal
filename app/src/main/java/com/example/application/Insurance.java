package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Insurance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar1;
    private NavigationView new_view;
     CardView card1,card2,card3,card4,card5,card6;
     public Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);


            toolbar1 = findViewById(R.id.toolbar);
            drawerLayout = findViewById(R.id.ins);
            new_view = findViewById(R.id.nav_view);
            setSupportActionBar(toolbar1);
            getSupportActionBar().setTitle("JUH Application");

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Insurance.this, drawerLayout, toolbar1, R.string.navigation_open, R.string.navigation_close);

            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            new_view.setNavigationItemSelectedListener(this);

            dialog = new Dialog(this);

            card1 = (CardView) findViewById(R.id.card);
            card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       openA();
                }
            });

            card2 = (CardView) findViewById(R.id.card1);
            card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openB();
            }
        });

            card3 = (CardView) findViewById(R.id.card2);
            card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openC();
            }
        });

            card4 = (CardView) findViewById(R.id.card3);
            card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openD();
            }
        });

             card5 = (CardView) findViewById(R.id.card4);
             card5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                openE();
            }
        });

            card6 = (CardView) findViewById(R.id.card5);
            card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openF();
            }
        });



        }

    private void openF() {
        dialog.setContentView(R.layout.f);

        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openE() {
        dialog.setContentView(R.layout.e);

        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openD() {

        dialog.setContentView(R.layout.d);

        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openC() {
        dialog.setContentView(R.layout.c);

        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void openB() {

        dialog.setContentView(R.layout.b);

        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openA() {

        dialog.setContentView(R.layout.a);


        TextView t = dialog.findViewById(R.id.textView9);
        TextView t1 = dialog.findViewById(R.id.textView10);
        TextView t2 = dialog.findViewById(R.id.textView11);
        TextView t3 = dialog.findViewById(R.id.textView12);
        TextView t4 = dialog.findViewById(R.id.textView13);
        Button b = dialog.findViewById(R.id.button);

        dialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    public boolean onCreateOptionMenu(Menu menu) {
            MenuInflater ObjMenuInflater = getMenuInflater();
            ObjMenuInflater.inflate(R.menu.main_mune,menu);

            return super.onCreateOptionsMenu(menu);
        }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){

                case R.id.us:
            Intent i2 = new Intent(Insurance.this,contact_us.class);
            startActivity(i2);
            finish();
            break;

            case R.id.Log_Out:
            Intent i = new Intent(Insurance.this,MainActivity.class);
            startActivity(i);
            finish();
            break;

            case R.id.Clinics:
            Intent i1 = new Intent(Insurance.this,Clinics2.class);
            startActivity(i1);
            finish();
            break;

            case R.id.Profile:
            Intent i3 = new Intent(Insurance.this,profile_patient.class);
            startActivity(i3);
            finish();
            break;

            case R.id.LabTest:
            Intent i4 = new Intent(Insurance.this,lab_test_info.class);
            startActivity(i4);
            finish();
            break;

            case R.id.result:
            Intent i5 = new Intent(Insurance.this,Lab.class);
            startActivity(i5);
            finish();
            break;

            case R.id.home:
            Intent i6 = new Intent(Insurance.this,home.class);
            startActivity(i6);
            finish();
            break;

                case R.id.feedback:
                    Intent i7 = new Intent(Insurance.this,feesback.class);
                    startActivity(i7);
                    finish();
                    break;

                case R.id.off:
                    Intent i9 = new Intent(Insurance.this,Off.class);
                    startActivity(i9);
                    finish();
                    break;

                default:
                    return super.onOptionsItemSelected(item);
            }
            return true;
        }


}
