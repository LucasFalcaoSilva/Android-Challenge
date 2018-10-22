package com.exercise.androidchallenge.repository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.exercise.androidchallenge.repository.dao.UsuarioDao;

public class DatabaseHelper extends DBMainHelper {

    private final static String DB = "challenge.db";
    private final static int VERSAO = 1;


    public DatabaseHelper(Context context) {
        super(context, DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDao.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
