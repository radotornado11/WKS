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

    public static final String TABLE_TIMETABLE = "terminarz";
    public static final String COLUMN_TIMETABLE_ID = "id_terminarza";
    public static final String COLUMN_HOST = "gospodarz";
    public static final String COLUMN_GUEST = "gosc";
    public static final String COLUMN_DATE = "data";
    public static final String COLUMN_TIME = "czas";
    public static final String COLUMN_RESULT_HOME = "bramki_dom";
    public static final String COLUMN_RESULT_AWAY = "bramki_wyjazd";

    public static final String TABLE_EVENT = "wydarzenie";
    public static final String COLUMN_EVENT_ID = "id_wydarzenia";
    public static final String COLUMN_EVENT_HOST = "gospodarz";
    public static final String COLUMN_EVENT_GUEST = "gosc";
    public static final String COLUMN_EVENT_DATE = "data";
    public static final String COLUMN_EVENT_TIME = "czas";
    public static final String COLUMN_EVENT_RESULT_HOME = "bramki_dom";
    public static final String COLUMN_EVENT_RESULT_AWAY = "bramki_wyjazd";

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

    private static final String SQL_CREATE_TIMETABLE_TABLE = "CREATE TABLE "+ TABLE_TIMETABLE + "("
            + COLUMN_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_HOST + " TEXT NOT NULL, "
            + COLUMN_GUEST + " TEXT NOT NULL, "
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_TIME + " TEXT NOT NULL, "
            + COLUMN_RESULT_HOME + " TEXT, "
            + COLUMN_RESULT_AWAY + " TEXT "
            + ");";

    private static final String SQL_CREATE_EVENT_TABLE = "CREATE TABLE "+ TABLE_EVENT + "("
            + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EVENT_HOST + " TEXT NOT NULL, "
            + COLUMN_EVENT_GUEST + " TEXT NOT NULL, "
            + COLUMN_EVENT_DATE + " TEXT NOT NULL, "
            + COLUMN_EVENT_TIME + " TEXT NOT NULL, "
            + COLUMN_EVENT_RESULT_HOME + " TEXT, "
            + COLUMN_EVENT_RESULT_AWAY + " TEXT "
            + ");";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TABLE);
        db.execSQL(SQL_CREATE_TIMETABLE_TABLE);
        db.execSQL(SQL_CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TIMETABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
}
