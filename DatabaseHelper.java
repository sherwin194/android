package com.example.exampractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    //to create SQLite database
    public DatabaseHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(userName varchar(20),phoneNum varchar(10),userAddress varchar(30))");
    }

    //drop the table when the version changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    //to save the user data
    public long saveNewUserData(String name, String phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName",name);
        cv.put("phoneNum",phone);
        cv.put("userAddress",address);

        long recordId = db.insert("users",null,cv);

        return recordId;
    }

    //to view all records from users table
    public String getAllRecord(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users", null);
        String output = "";
        while(cursor.moveToNext()){
            String userName = cursor.getString(0);
            String phoneNum = cursor.getString(1);
            String userAddress = cursor.getString(2);
            output = output + userName+" - "+phoneNum+" - "+userAddress+"\n";
        }
        return output;
    }
}
