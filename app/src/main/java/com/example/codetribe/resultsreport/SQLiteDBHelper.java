package com.example.codetribe.resultsreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codetribe on 8/15/2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "profile";
    public static final String COLUMN_ID = "userid";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MOBILE = "mobile";

    public static final String TABLE_NEW = "people";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "TVSHOW";
    public static final String COL5 = "COMMENTS";
    public static final String COL6 = "GRADE";
    public static final String COL7 = "SEMESTER";
    public static final String COL8 = "SUBJECT";


    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULLNAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_MOBILE + " TEXT " + ")";



    //modified constructor
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
        String createTable = "CREATE TABLE " + TABLE_NEW + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT, STUDENTNO TEXT, COMMENTS TEXT , GRADE TEXT , SEMESTER TEXT, SUBJECT TEXT,MARKS TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NEW);
        onCreate(sqLiteDatabase);

    }


    public boolean addData(String name, String studentno, String marks,String comments,String grade,String semester,String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,studentno);
        contentValues.put(COL4, marks);
        contentValues.put(COL5, comments);
        contentValues.put(COL6, grade);
        contentValues.put(COL7, semester);
        contentValues.put(COL8, subject);

        long result  = db.insert(TABLE_NEW, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public ResultsClass showData() {
        List<ResultsClass> users = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NEW, null);
        ResultsClass users1=null;
        if (data.moveToFirst()) {
            do {
                users1 = new ResultsClass();
                users1.setStudentno(data.getInt(data.getColumnIndex(COL2)));
                users1.setName(data.getString(data.getColumnIndex(COL3)));
                users1.setMarks(data.getString(data.getColumnIndex(COL4)));
                users1.setSubject(data.getInt(data.getColumnIndex(COL5)));
                users1.setComments(data.getString(data.getColumnIndex(COL6)));
                users1.setSemester(data.getInt(data.getColumnIndex(COL7)));
                users1.setGrade(data.getInt(data.getColumnIndex(COL8)));
               // users1.setEtId(data.getInt(data.getColumnIndex(COL8)));


            } while (data.moveToNext());
        }
        return users1;
    }

    public boolean updateData(String id, Editable text, String name, String studentno, String marks, String comments, String grade, String semester, String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL1,id);
        contentValues.put(COL2,studentno);
        contentValues.put(COL3,name);
        contentValues.put(COL4, grade);
        contentValues.put(COL5,marks);
        contentValues.put(COL6, subject);
        contentValues.put(COL7, semester);
        contentValues.put(COL8, comments);



        db.update(TABLE_NEW, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NEW, "ID = ?", new String[] {id});
    }

}