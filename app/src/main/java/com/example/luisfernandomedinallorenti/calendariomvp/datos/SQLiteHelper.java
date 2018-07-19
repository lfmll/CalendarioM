package com.example.luisfernandomedinallorenti.calendariomvp.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=1;

    public SQLiteHelper(Context context) {
        super(context, SQLConstantes.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLConstantes.SQL_CREATE_TABLE_EVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLConstantes.SQL_DELETE);
        onCreate(db);
    }
}
