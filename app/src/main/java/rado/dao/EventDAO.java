package rado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import rado.wks.Timetable;

/**
 * Created by Rado on 21.11.2017.
 */

public class EventDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;
    private final int COUNT_OF_MATCHES = 30;
    private String[] allColumns = {dbHelper.COLUMN_EVENT_ID, dbHelper.COLUMN_EVENT_HOST, dbHelper.COLUMN_EVENT_GUEST,
            dbHelper.COLUMN_EVENT_DATE, dbHelper.COLUMN_EVENT_TIME, dbHelper.COLUMN_EVENT_RESULT_HOME, dbHelper.COLUMN_EVENT_RESULT_AWAY};
    public static String DATE = "Data";
    public static String TIME = "Czas";
    public static String TEAM_HOME = "Drużyna dom";
    public static String TEAM_AWAY = "Drużyna wyjazd";
    public static String HOME_RESULT = "Bramki dom";
    public static String AWAY_RESULT = "Bramki wyjazd";

    public EventDAO(Context context)
    {
        dbHelper = new DBHelper(context);
        this.context = context;
        try {
            open();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean insertData(String host, String guest, String date, String time, String resHome, String resAway)
    {
        if(checkIfEventExists(date))
            return false;
        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COLUMN_EVENT_HOST, host);
            contentValues.put(dbHelper.COLUMN_EVENT_GUEST, guest);
            contentValues.put(dbHelper.COLUMN_EVENT_DATE, date);
            contentValues.put(dbHelper.COLUMN_EVENT_TIME, time);
            contentValues.put(dbHelper.COLUMN_EVENT_RESULT_HOME, resHome);
            contentValues.put(dbHelper.COLUMN_EVENT_RESULT_AWAY, resAway);

            //if(!checkIfRecordExists(database, dbHelper.COLUMN_EVENT_HOST, dbHelper.COLUMN_EVENT_GUEST)) {
            long result = database.insert(dbHelper.TABLE_EVENT, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

//    public boolean checkIfRecordExists(SQLiteDatabase db, String host, String guest){
//        Cursor c = db.rawQuery("SELECT COUNT(1) FROM "+ dbHelper.TABLE_EVENT + " WHERE gospodarz = "+host+" AND gosc = "+guest, null);
//        c.moveToFirst();
//        return c.getInt(0)==0?false:true;
//    }
//
//    public boolean doesTableExist(SQLiteDatabase db, String tableName) {
//        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
//
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                cursor.close();
//                return true;
//            }
//            cursor.close();
//        }
//        return false;
//    }

    public ArrayList<HashMap<String, String>> getAllEventsList()
    {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor cursor = database.query(dbHelper.TABLE_EVENT, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(TEAM_HOME, cursor.getString(1));
            hashMap.put(TEAM_AWAY, cursor.getString(2));
            hashMap.put(DATE, cursor.getString(3));
            hashMap.put(TIME, cursor.getString(4));
            hashMap.put(HOME_RESULT, cursor.getString(5));
            hashMap.put(AWAY_RESULT, cursor.getString(6));

            arrayList.add(hashMap);
            cursor.moveToNext();
        }
        return arrayList;
    }

    public boolean checkIfEventExists(String date){
        String query = "SELECT * FROM " + dbHelper.TABLE_EVENT + " WHERE " + dbHelper.COLUMN_EVENT_DATE + " = '" + date +"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        return true;
    }
}
