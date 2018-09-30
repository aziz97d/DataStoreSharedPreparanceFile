package com.example.abdulaziz.projectone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView homeCardView, hospitalCardView, officeCardView, schoolCardView, restaurantCardView, shoppingCardView;
    private LinearLayout layoutMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutMainActivity = findViewById(R.id.mainActivityId);

        homeCardView = findViewById(R.id.homeCardViewId);
        hospitalCardView = findViewById(R.id.hospitalCardViewId);
        officeCardView = findViewById(R.id.officeCardViewId);
        schoolCardView = findViewById(R.id.schoolCardViewId);
        restaurantCardView = findViewById(R.id.restaurantCardViewId);
        shoppingCardView = findViewById(R.id.shoppingCardViewId);

        homeCardView.setOnClickListener(this);
        hospitalCardView.setOnClickListener(this);
        officeCardView.setOnClickListener(this);
        schoolCardView.setOnClickListener(this);
        restaurantCardView.setOnClickListener(this);
        shoppingCardView.setOnClickListener(this);

        if (getColor()!=0){
            layoutMainActivity.setBackgroundColor(getColor());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.homeCardViewId){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.hospitalCardViewId){
            Intent intent = new Intent(MainActivity.this,HospitalActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.officeCardViewId){
            Intent intent = new Intent(MainActivity.this,OfficeActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.schoolCardViewId){
            Intent intent = new Intent(MainActivity.this,SchoolActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.restaurantCardViewId){
            Intent intent = new Intent(MainActivity.this,RestaurantActivity.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.shoppingCardViewId){
            Intent intent = new Intent(MainActivity.this,ShoppingActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.gray_color){
            layoutMainActivity.setBackgroundColor(getResources().getColor(R.color.colorGray));
            saveColor(getResources().getColor(R.color.colorGray));
        }
        else if (item.getItemId()==R.id.green_color){
            int color = getResources().getColor(R.color.colorGreen);
            layoutMainActivity.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            saveColor(color);
        }
        else if (item.getItemId()==R.id.orange_color){
            int color = getResources().getColor(R.color.colorOrange);
            layoutMainActivity.setBackgroundColor(color);
            saveColor(color);

        }
        else if (item.getItemId()==R.id.purple_color){
            int color = getResources().getColor(R.color.colorPurple);
            layoutMainActivity.setBackgroundColor(color);
            saveColor(color);
        }
        else if (item.getItemId()==R.id.default_color){
            int color = getResources().getColor(R.color.colorWhite);
            layoutMainActivity.setBackgroundColor(color);
            saveColor(color);
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveColor(int color){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveColor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("savedColor",color);
        editor.commit();
    }

    public int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveColor",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("savedColor",0);
        //return sharedPreferences.getInt("savedColor",getResources().getColor(R.color.colorPrimary));
    }
}
