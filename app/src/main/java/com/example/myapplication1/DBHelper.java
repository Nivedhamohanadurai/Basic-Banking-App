package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MY_CUSTOMER_LIST.DB";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "CUSTOMERS";
    static final String NAME = "NAME";
    static final String EMAIL = "EMAIL";
    static final double CURRENTBAL = 0;
    private static final String Create_DB_query = "CREATE TABLE " + DATABASE_TABLE +
            "("+NAME+" TEXT NOT NULL, "+EMAIL+" TEXT NOT NULL, " +
            "CURRENTBAL DOUBLE NOT NULL);";

    public DBHelper(Context context) {
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

    public Boolean insert(String name, String email, double currentbal){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put("CURRENTBAL",currentbal);
        long result=db.insert(DATABASE_TABLE,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Boolean update(String name, String email, double currentbal){
        SQLiteDatabase db=this.getWritableDatabase(); int flag=1;
        ContentValues contentValues=new ContentValues();
        contentValues.put(EMAIL,email);
        contentValues.put("CURRENTBAL",currentbal);
        Cursor cursor=db.rawQuery("select * from "+DATABASE_TABLE+" where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.update(DATABASE_TABLE, contentValues,"name=?",new String[]{name});
            if (result == -1)
                flag=0;
        }
        if(flag==1)
            return true;
        else
            return false;
    }
    public Cursor fetch(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DATABASE_TABLE,null);
        return cursor;
    }
}
