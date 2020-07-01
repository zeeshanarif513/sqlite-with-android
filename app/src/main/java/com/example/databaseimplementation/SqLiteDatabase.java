package com.example.databaseimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqLiteDatabase extends SQLiteOpenHelper {
    SQLiteDatabase db;

    //db name
    private static final String DATABASE_NAME = "Student_Info";

    //db version
    private static final int VERSION = 1;

    //table name
    private static final String TABLE_NAME = "Student";

    //Student Table Columns
    public static final String KEY_ID = "StudentID";
    public static final String KEY_FNAME = "FirstName";
    public static final String KEY_LNAME = "LastName";


    public SqLiteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_TABLE= "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID
                + " TEXT PRIMARY KEY, " + KEY_FNAME + " TEXT, " + KEY_LNAME + " TEXT); ";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public long insertStudent(String id, String firstname, String lastname) {
        db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(KEY_FNAME,firstname);
        cv.put(KEY_LNAME,lastname);
        return db.insert(TABLE_NAME, null, cv);
    }

    public String viewData() {
        db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_ID,KEY_FNAME,KEY_LNAME};
        Cursor c=db.query(TABLE_NAME,columns,null,null,null,null,null);

        int iID = c.getColumnIndex(KEY_ID);
        int iFname = c.getColumnIndex(KEY_FNAME);
        int iLname = c.getColumnIndex(KEY_LNAME);

        String result = "";

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = result + c.getString(iID) + " " + c.getString(iFname) + " "+ c.getString(iLname) + "\n";
        }
        db.close();
        return result;
    }

    public String getFirstName(String id) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_ID,KEY_FNAME,KEY_LNAME};
        Cursor c=db.query(TABLE_NAME,columns,KEY_ID + " = " + id,null,null,null,null);

        if(c!=null && c.moveToFirst()) {

            int iFname = c.getColumnIndex(KEY_FNAME);
            String fname = c.getString(iFname);
            db.close();
            return fname;

        }
        Log.d("Testing","abc");
        db.close();
        return null;

    }

    public String getLastName(String id) {
        db = this.getReadableDatabase();
        String[] columns = new String[]{KEY_ID,KEY_FNAME,KEY_LNAME};
        Cursor c=db.query(TABLE_NAME,columns,KEY_ID + " = " + id,null,null,null,null);

        if(c!=null && c.moveToFirst()) {

            int iLname = c.getColumnIndex(KEY_LNAME);
            String lname = c.getString(iLname);
            db.close();
            return lname;

        }
        db.close();
        return null;

    }

    public void UpdateStudent(String id, String firstname, String lastname) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_FNAME,firstname);
        cv.put(KEY_LNAME,lastname);
        db.update(TABLE_NAME ,cv ,KEY_ID + " = " + id ,null);
        db.close();
    }

    public void DeleteStudent(String id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = " + id ,null);
    }
}
