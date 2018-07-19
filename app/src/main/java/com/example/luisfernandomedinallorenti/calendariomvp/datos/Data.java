package com.example.luisfernandomedinallorenti.calendariomvp.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luisfernandomedinallorenti.calendariomvp.modelos.Evento;

public class Data {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public Data(Context context) {
        this.context = context;
        sqLiteOpenHelper=new SQLiteHelper(context);
        sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();
    }

    public void open(){
        sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteOpenHelper.close();
    }

    public void insertarEvento(Evento evento){
        ContentValues values=evento.toValues();
        sqLiteDatabase.insert(SQLConstantes.TABLE_EVENTO,null,values);
    }
    public Evento getEvento(String nombre){
        Evento evento=new Evento();
        String[] whereArgs=new String[]{nombre};
        Cursor cursor=sqLiteDatabase.query(
                SQLConstantes.TABLE_EVENTO,
                SQLConstantes.ALL_COLUMNS,
                SQLConstantes.SEARCH_BY_NOMBRE,
                whereArgs,
                null,null,null);
        while (cursor.moveToNext()){
            evento.setId(cursor.getInt(cursor.getColumnIndex(SQLConstantes.COLUMN_ID)));
            evento.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.COLUMN_NOMBRE)));
        }
        return evento;
    }
    public Cursor getAll(){
        String[] whereArgs=new String[]{};
        Cursor cursor=sqLiteDatabase.query(
                SQLConstantes.TABLE_EVENTO,
                SQLConstantes.ALL_COLUMNS,
                null,null,null,null,null);
        return cursor;

    }
    public Cursor getTiempo(String fecha, String hora){
        String[] whereArgs=new String[]{fecha,hora};
        Cursor cursor=sqLiteDatabase.query(
                SQLConstantes.TABLE_EVENTO,
                SQLConstantes.ALL_COLUMNS,
                SQLConstantes.SEARCH_BY_FECHAHORA,
                whereArgs,
                null,null,null
        );
        return cursor;
    }
}
