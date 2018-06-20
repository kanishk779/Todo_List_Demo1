package com.example.android.todo_list.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.todo_list.Models.TaskDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 19-06-2018.
 */

public class TaskDatabase extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "taskdatabse";
    private static  final String TABLE_NAME = "tasktable";
    private static  final String HEADING_COLUMN = "heading";
    private static  final String DATE_COLUMN = "date";
    private static  final String TIME_COLUMN = "time";
    private static  final String DESCRIPTION_COLUMN = "description";
    private static  final Integer VERSION = 1;
    private static  final String CREATE_TABLE = "create table "+TABLE_NAME+" ( "+HEADING_COLUMN+" varchar(20),"+DATE_COLUMN+" varchar(20),"+TIME_COLUMN+" varchar(20), "+DESCRIPTION_COLUMN+" varchar(100) );";
    public TaskDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    private SQLiteDatabase sdW,sdR;
    public void openWrite(){
        sdW= getWritableDatabase();
    }
    public void closeWrite(){
        sdW.close();
    }
    public void openRead(){
        sdR= getReadableDatabase();
    }
    public void closeRead(){
        sdR.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME+"");
    }
    public long insert(TaskDetails td){
        ContentValues cv = new ContentValues();
        cv.put(HEADING_COLUMN,td.getTaskHeading());
        cv.put(DATE_COLUMN,td.getDate());
        cv.put(TIME_COLUMN,td.getTime());
        cv.put(DESCRIPTION_COLUMN,td.getDescription());
        long i=0;
        try{
            i = sdW.insert(TABLE_NAME,null,cv);
        }
        catch(Exception e){
            Log.i("error",e.toString());
        }
        return i;
    }
    public long delete(String heading){
        return sdW.delete(TABLE_NAME,""+HEADING_COLUMN+"=?",new String[]{heading});
    }
    public TaskDetails read(String heading){
        sdR=getReadableDatabase();
        TaskDetails t = new TaskDetails();
        Cursor c=sdR.query(TABLE_NAME,null,""+HEADING_COLUMN+"=?",new String[]{heading},null,null,null);
        if(c!=null)
        {
            c.moveToNext();
            t.setTaskHeading(c.getString(0));
            t.setDate(c.getString(1));
            t.setTime(c.getString(2));
            t.setDescription(c.getString(3));
            c.close();
        }

        return  t;
    }
    public ArrayList<TaskDetails> getAllTasks() {
        ArrayList<TaskDetails> list = new ArrayList<>();
        //list =null;
        try {
            Cursor c = sdR.query(TABLE_NAME, null, null, null, null, null, null);
            while (c.moveToNext()) {
                Log.e("error","inside getAllTasks");
                TaskDetails t = new TaskDetails();
                t.setTaskHeading(c.getString(0));
                t.setDate(c.getString(1));
                t.setTime(c.getString(2));
                t.setDescription(c.getString(3));
                list.add(t);
            }
            c.close();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return list;
    }
}
