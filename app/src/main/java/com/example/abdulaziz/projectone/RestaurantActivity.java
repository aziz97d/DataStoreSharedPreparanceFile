package com.example.abdulaziz.projectone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.sql.StatementEvent;

public class RestaurantActivity extends AppCompatActivity {

    private Button btnAddItem, btnShowItem;
    private EditText etItemName, etCategory, etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);


        final SqliteDB sqliteDB = new SqliteDB(this);
        SQLiteDatabase sqLiteDatabase = sqliteDB.getWritableDatabase();

        etItemName = findViewById(R.id.et_item_name);
        etCategory = findViewById(R.id.et_category);
        etPrice = findViewById(R.id.et_price);

        btnAddItem = findViewById(R.id.btn_add_item);
        btnShowItem = findViewById(R.id.btn_show_item);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemName = etItemName.getText().toString();
                String itemCategory = etCategory.getText().toString();
                String itemPrice = etPrice.getText().toString();

                if (itemName.equals("") && itemCategory.equals("") && itemPrice.equals("") ){
                    Toast.makeText(RestaurantActivity.this, "Please Enter Name & Category & Price", Toast.LENGTH_SHORT).show();
                }
                else {
                    long rowAffect = sqliteDB.insertDate(itemName,itemCategory,itemPrice);

                    if (rowAffect>0){
                        Toast.makeText(RestaurantActivity.this, rowAffect+" : Item Added", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(RestaurantActivity.this, rowAffect+" : Item Added Failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnShowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor allItem = sqliteDB.getAllItem();

                if (allItem.getCount()!=0){
                    StringBuffer stringBuffer = new StringBuffer();
                    while (allItem.moveToNext()){
                        stringBuffer.append("ID : "+allItem.getString(0)+"\n");
                        stringBuffer.append("Name : "+allItem.getString(1)+"\n");
                        stringBuffer.append("Category : " + allItem.getString(2)+"\n");
                        stringBuffer.append("Price : "+allItem.getString(3)+"\n"+"\n");
                    }

                    showData("All Item",stringBuffer.toString());

                }
                else {
                    showData("All Item", "No Data Found");
                }
            }
        });
    }

    public void showData(String title, String data){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RestaurantActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setIcon(R.drawable.show_item_icon);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(data);
        alertDialog.show();
    }
}
