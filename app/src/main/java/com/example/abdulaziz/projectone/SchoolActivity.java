package com.example.abdulaziz.projectone;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SchoolActivity extends AppCompatActivity {

    private EditText etNote;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        etNote = findViewById(R.id.txt_note);
        btnSave = findViewById(R.id.btn_save_note);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile();
            }
        });

        readFromFile();

    }

    public void writeToFile(){
        String note = etNote.getText().toString();

        if (note != "") {
            try {
                FileOutputStream fileOutputStream = openFileOutput("note.txt", Context.MODE_PRIVATE);
                fileOutputStream.write(note.getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "File Saved.", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(this, "Please Write something", Toast.LENGTH_SHORT).show();
        }
    }

    public void readFromFile(){
        try {
            FileInputStream fileInputStream = openFileInput("note.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\n");
            }

            etNote.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
