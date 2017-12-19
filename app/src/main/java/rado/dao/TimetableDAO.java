package rado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import rado.wks.Table;
import rado.wks.Timetable;

/**
 * Created by Rado on 13.11.2017.
 */

public class TimetableDAO {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;
    private final int COUNT_OF_MATCHES = 30;
    private String[] allColumns = {dbHelper.COLUMN_TIMETABLE_ID, dbHelper.COLUMN_HOST, dbHelper.COLUMN_GUEST,
            dbHelper.COLUMN_DATE, dbHelper.COLUMN_TIME, dbHelper.COLUMN_RESULT_HOME, dbHelper.COLUMN_RESULT_AWAY};
    public static String DATE = "Data";
    public static String TIME = "Czas";
    public static String TEAM_HOME = "Drużyna dom";
    public static String TEAM_AWAY = "Drużyna wyjazd";
    public static String HOME_RESULT = "Bramki dom";
    public static String AWAY_RESULT = "Bramki wyjazd";

    public TimetableDAO(Context context)
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
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_HOST, host);
        contentValues.put(dbHelper.COLUMN_GUEST, guest);
        contentValues.put(dbHelper.COLUMN_DATE, date);
        contentValues.put(dbHelper.COLUMN_TIME, time);
        contentValues.put(dbHelper.COLUMN_RESULT_HOME, resHome);
        contentValues.put(dbHelper.COLUMN_RESULT_AWAY, resAway);

        if(!doesTableExist(database, dbHelper.TABLE_TIMETABLE) || getProfilesCount() < COUNT_OF_MATCHES) {
            long result = database.insert(dbHelper.TABLE_TIMETABLE, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
        else
        {
            //poprawić update
            database.update(dbHelper.TABLE_TIMETABLE, contentValues, "id_terminarza=?", new String[]{dbHelper.COLUMN_TIMETABLE_ID});
        }
        return true;
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public boolean doesTableExist(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public long getProfilesCount() {
        long cnt  = DatabaseUtils.queryNumEntries(database, dbHelper.TABLE_TIMETABLE);
        return cnt;
    }

    public ArrayList<HashMap<String, String>> getAllTimetableList()
    {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor cursor = database.query(dbHelper.TABLE_TIMETABLE, allColumns, null, null, null, null, null);
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
}
