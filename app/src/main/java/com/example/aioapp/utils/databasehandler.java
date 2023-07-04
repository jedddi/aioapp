package com.example.aioapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aioapp.Model.todomodel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class databasehandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "ToDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "ID";
    private static final String TASK = "TASK";
    private static final String STATUS = "STATUS";
//    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                                              + TASK + "TEXT, " + STATUS + "INTEGER)";
    private SQLiteDatabase db;
    public databasehandler(@Nullable Context context){
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS todo (ID INTEGER PRIMARY KEY AUTOINCREMENT , TASK TEXT , STATUS INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //drops table
        db.execSQL("DROP TABLE IF EXISTS todo");
        //CREATE TABLE
        onCreate(db);
    }
    public void insertTask(todomodel task){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }
    public void updateStatus(int id, int status){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, "ID=?", new String[] {String.valueOf(id)});
    }
    public void updateTask(int id, String task){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, "ID=?", new String[] {String.valueOf(id)});
    }
    public void deleteTask(int id){
        db = this.getWritableDatabase();
        db.delete(TODO_TABLE, "ID=?", new String[] {String.valueOf(id)});
    }

    public List<todomodel> getAllTask(){
        db = this.getWritableDatabase();
        List<todomodel> taskList = new ArrayList<>();
        Cursor cur = null;

        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE , null , null , null , null , null , null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        todomodel task = new todomodel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }while(cur.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            cur.close();
        }
        return taskList;
    }

}
