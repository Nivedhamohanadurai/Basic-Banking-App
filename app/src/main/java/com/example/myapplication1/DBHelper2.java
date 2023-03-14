package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MY_TRANSACTION_LIST.DB";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "TRANSACTIONS";
    static final String FROMNAME = "FROMNAME";
    static final String TONAME = "TONAME";
    static final double AMOUNT = 0;
    private static final String Create_DB_query = "CREATE TABLE "+ DATABASE_TABLE +
            "("+FROMNAME+" TEXT NOT NULL, "+TONAME+" TEXT NOT NULL, " +
            "AMOUNT DOUBLE NOT NULL);";

    public DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_DB_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
    }

    public Boolean insert(String fromname, String toname, double amount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FROMNAME,fromname);
        contentValues.put(TONAME,toname);
        contentValues.put("AMOUNT",amount);
        long result=db.insert(DATABASE_TABLE,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor fetch(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DATABASE_TABLE,null);
        return cursor;
    }
}
