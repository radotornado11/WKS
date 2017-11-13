package rado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rado.model.Player;
import rado.wks.Table;

/**
 * Created by Rado on 13.11.2017.
 */

public class TableDAO {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;
    private final int COUNT_OF_TEAMS = 16;
    private String[] allColumns = {dbHelper.COLUMN_TABLE_ID, dbHelper.COLUMN_TABLE_POSITION, dbHelper.COLUMN_CLUB_ID_FK,
        dbHelper.COLUMN_POINTS_NUMBER, dbHelper.COLUMN_MATCHES_NUMBER, dbHelper.COLUMN_WINS_NUMBER,
        dbHelper.COLUMN_DRAWS_NUMBER, dbHelper.COLUMN_LOSES_NUMBER, dbHelper.COLUMN_GOALS_BALANCE};

    public TableDAO(Context context)
    {
        dbHelper = new DBHelper(context);
        this.context = context;
        try {
            open();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean insertData(String pos, String clubName, String points, String matches, String wins, String draws, String loses, String balance)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_TABLE_POSITION, pos);
        contentValues.put(dbHelper.COLUMN_CLUB_ID_FK, clubName);
        contentValues.put(dbHelper.COLUMN_POINTS_NUMBER, points);
        contentValues.put(dbHelper.COLUMN_MATCHES_NUMBER, matches);
        contentValues.put(dbHelper.COLUMN_WINS_NUMBER, wins);
        contentValues.put(dbHelper.COLUMN_DRAWS_NUMBER, draws);
        contentValues.put(dbHelper.COLUMN_LOSES_NUMBER, loses);
        contentValues.put(dbHelper.COLUMN_GOALS_BALANCE, balance);

        if(!doesTableExist(database, dbHelper.TABLE_TABLE) || getProfilesCount() < COUNT_OF_TEAMS) {
            long result = database.insert(dbHelper.TABLE_TABLE, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
        else
        {
            database.update(dbHelper.TABLE_TABLE, contentValues, "id_tabeli=?", new String[]{dbHelper.COLUMN_TABLE_ID});
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
        long cnt  = DatabaseUtils.queryNumEntries(database, dbHelper.TABLE_TABLE);
        return cnt;
    }

    public ArrayList<HashMap<String, String>> getAllTableList()
    {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor cursor = database.query(dbHelper.TABLE_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(Table.RANK, cursor.getString(1));
            hashMap.put(Table.TEAM, cursor.getString(2));
            hashMap.put(Table.POINTS, cursor.getString(3));
            hashMap.put(Table.MATCHES, cursor.getString(4));
            hashMap.put(Table.WINS, cursor.getString(5));
            hashMap.put(Table.DRAWS, cursor.getString(6));
            hashMap.put(Table.LOSES, cursor.getString(7));
            hashMap.put(Table.GOALS, cursor.getString(8));
            arrayList.add(hashMap);
            cursor.moveToNext();
        }
        return arrayList;
    }

}
