package rado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rado on 14.11.2017.
 */

public class NewsDAO {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;
    private final int COUNT_OF_NEWS = 10;
    private String[] allColumns = {dbHelper.COLUMN_NEWS_ID, dbHelper.COLUMN_NEWS_NR, dbHelper.COLUMN_NEWS_HEAD,
            dbHelper.COLUMN_NEWS_BODY};
    public static String ID_NEWSA = "Id";
    public static String NR_NEWSA = "Numer";
    public static String HEAD_NEWSA = "Nagłowek";
    public static String BODY_NEWSA = "News";


    public NewsDAO(Context context)
    {
        dbHelper = new DBHelper(context);
        this.context = context;
        try {
            open();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean insertData(String nr, String head, String body)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_NEWS_NR, nr);
        contentValues.put(dbHelper.COLUMN_NEWS_HEAD, head);
        contentValues.put(dbHelper.COLUMN_NEWS_BODY, body);

        if(!doesTableExist(database, dbHelper.TABLE_NEWS) || getProfilesCount() < COUNT_OF_NEWS) {
            long result = database.insert(dbHelper.TABLE_NEWS, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
        else
        {
            //poprawić update
//            Log.d("DUPA", "JESTEM TU" + clubName);
            database.update(dbHelper.TABLE_NEWS, contentValues, "nr_newsa=?", new String[]{dbHelper.COLUMN_NEWS_NR});
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
        long cnt  = DatabaseUtils.queryNumEntries(database, dbHelper.TABLE_NEWS);
        return cnt;
    }

    public ArrayList<HashMap<String, String>> getAllTableList()
    {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        Cursor cursor = database.query(dbHelper.TABLE_NEWS, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(NR_NEWSA, cursor.getString(1));
            hashMap.put(HEAD_NEWSA, cursor.getString(2));
            hashMap.put(BODY_NEWSA, cursor.getString(3));

            arrayList.add(hashMap);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
