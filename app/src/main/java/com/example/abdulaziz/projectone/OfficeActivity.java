package com.example.abdulaziz.projectone;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OfficeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etPhone;
    private Button btnSave, btnLoad;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //hiding the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hiding the action bar
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        btnSave = findViewById(R.id.btn_save);
        btnLoad = findViewById(R.id.btn_load);
        txtShow = findViewById(R.id.txt_show);


        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_save){

            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();

            if (name.equals("") || phone.equals("")){
                Toast.makeText(this, "Please enter name and phone", Toast.LENGTH_SHORT).show();
            }
            else {

                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("phone",phone);
                editor.commit();
                txtShow.setText("");
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();

            }


        }
        else if (view.getId()==R.id.btn_load){

            SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);
            if (sharedPreferences.contains("name") && sharedPreferences.contains("phone")){
                String name = sharedPreferences.getString("name","Data not Found");
                String phone = sharedPreferences.getString("phone","Data not Found");

                txtShow.setText("Name : "+name+"\n"+"Phone : "+phone);
            }
        }
    }
}
