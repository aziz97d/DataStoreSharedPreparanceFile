package com.example.abdulaziz.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class SqliteDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ResturentBD";
    public static final String TABLE_NAME = "Resturent_table";
    public static final String ID = "_id";
    public static final String ITEM_NAME = "_name";
    public static final String CATEGORY = "_category";
    public static final String PRICE = "_price";
    public static final int VERSION = 2;
    public static final String CREATE_TABLE = "Create Table "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ITEM_NAME+" VARCHAR(200), "+CATEGORY+" VARCHAR(200), "+PRICE+" INTEGER)";
    public static final String GET_ALL_ITEM = "SELECT * FROM "+TABLE_NAME;


    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public Context context;

    public SqliteDB(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try
        {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public long insertDate(String itemName, String itemCategory, String itemPrice){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME,itemName);
        contentValues.put(CATEGORY,itemCategory);
        contentValues.put(PRICE,itemPrice);

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor getAllItem(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor getAllItem = sqLiteDatabase.rawQuery(GET_ALL_ITEM,null);
        return getAllItem;
    }
}
