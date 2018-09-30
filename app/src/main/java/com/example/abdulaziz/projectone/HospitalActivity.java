package com.example.abdulaziz.projectone;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HospitalActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnIncrease, btnDecrease;
    private TextView txtPatient;
    private int patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        btnIncrease = findViewById(R.id.btn_increase);
        btnDecrease = findViewById(R.id.btn_decrese);
        txtPatient = findViewById(R.id.txt_patiant);

        if (loadPatients()!=0){
            txtPatient.setText("Total Patients : "+loadPatients());
            patients = loadPatients();
        }

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.btn_increase){
            patients+=1;
            txtPatient.setText("Total Patients : "+patients);
            savePatients(patients);

        }
        else if (view.getId()==R.id.btn_decrese){
            patients-=1;
            txtPatient.setText("Total Patients : "+patients);
            savePatients(patients);


        }
    }

    public void savePatients(int patients){
        SharedPreferences sharedPreferences = getSharedPreferences("Patients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalPatients",patients);
        editor.commit();
    }

    public int loadPatients(){
        SharedPreferences sharedPreferences = getSharedPreferences("Patients",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("totalPatients",0);

    }
}
