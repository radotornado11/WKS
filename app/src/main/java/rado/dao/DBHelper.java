package rado.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rado on 13.11.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    //public static final String TABLE = "liczba_meczów";

    public static final String TABLE_TABLE = "tabela";
    public static final String COLUMN_TABLE_ID = "id_tabeli";
    public static final String COLUMN_TABLE_POSITION = "pozycja";
    public static final String COLUMN_CLUB_ID_FK = "id_klubu";
    public static final String COLUMN_POINTS_NUMBER = "liczba_punktow";
    public static final String COLUMN_MATCHES_NUMBER = "liczba_meczów";
    public static final String COLUMN_WINS_NUMBER = "mecze_wygrane";
    public static final String COLUMN_DRAWS_NUMBER = "mecze_zremisowane";
    public static final String COLUMN_LOSES_NUMBER = "mecze_przegrane";
    public static final String COLUMN_GOALS_BALANCE = "bilans";

    private static final String DATABASE_NAME = "wks.db";
    private static final int DATABASE_VERSION = 1;

    //poprawić ID_FK
    private static final String SQL_CREATE_TABLE_TABLE = "CREATE TABLE "+ TABLE_TABLE + "("
            + COLUMN_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TABLE_POSITION + " INTEGER NOT NULL, "
            + COLUMN_CLUB_ID_FK + " TEXT NOT NULL, "
            + COLUMN_POINTS_NUMBER + " TEXT NOT NULL, "
            + COLUMN_MATCHES_NUMBER + " TEXT NOT NULL, "
            + COLUMN_WINS_NUMBER + " TEXT NOT NULL, "
            + COLUMN_DRAWS_NUMBER + " TEXT NOT NULL, "
            + COLUMN_LOSES_NUMBER + " TEXT NOT NULL, "
            + COLUMN_GOALS_BALANCE + " TEXT NOT NULL "
            + ");";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
}
