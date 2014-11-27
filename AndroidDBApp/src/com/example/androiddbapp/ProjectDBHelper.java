package com.example.androiddbapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.Context;
import java.util.HashMap;
import java.util.ArrayList;
import android.content.ContentValues;
import android.util.Log;

public class ProjectDBHelper extends SQLiteOpenHelper {

    public ProjectDBHelper(Context applicationContext) {
        super(applicationContext, "dbapp.db", null, 1);
    }
    
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE dbapp (id INTEGER PRIMARY KEY, message TEXT)";
        
        database.execSQL(query);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        
        String query = "DROP TABLE IF EXISTS dbapp";
        
        database.execSQL(query);
        onCreate(database);
    }
    
    public void insertRow(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        values.put("message", queryValues.get("message"));
        
        database.insert("dbapp", null, values);
        
        database.close();
    }
    
    public int updateRow(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        
        values.put("message", queryValues.get("message"));
        
        return database.update("dbapp", values, 
            "id=?", new String[] {queryValues.get("id")});
        
    }
    
    public void deleteRow(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        
        String deleteQuery = "DELETE FROM dbapp WHERE id='" + id + "'";
        
        database.execSQL(deleteQuery);
    }
    
    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> rowArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM dbapp";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> rowMap = new HashMap<String, String>();
                rowMap.put("id", cursor.getString(0));
                rowMap.put("message", cursor.getString(1));

                rowArrayList.add(rowMap);
            } while (cursor.moveToNext());
        }
        
        Log.d("LOOOG", "LIST: " + rowArrayList.toString());
        return rowArrayList;
    }
    
    public HashMap<String, String> getOne(String id) {
        HashMap<String, String> rowMap = new HashMap<String, String>();

        String selectQuery = "SELECT * FROM dbapp WHERE id='" + id + "'";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        
        if(cursor.moveToFirst()) {
            rowMap.put("id", cursor.getString(0));
            rowMap.put("message", cursor.getString(1));

        }
        
        return rowMap;
    }
}
