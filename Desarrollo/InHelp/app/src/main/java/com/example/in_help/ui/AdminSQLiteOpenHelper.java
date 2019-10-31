package com.example.in_help.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class AdminSQLiteOpenHelper extends  SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Velocidad(bitacora int, fecha text, velocidad real)");
        //db.execSQL("create table Giroscopio(bitacora int, fecha text, x real,y real,z real)");
        //db.execSQL("create table Acelerometro(bitacora int, fecha text, x real,y real,z real)");
        //db.execSQL("create table OBD2(bitacora int, fecha text, codifo text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
