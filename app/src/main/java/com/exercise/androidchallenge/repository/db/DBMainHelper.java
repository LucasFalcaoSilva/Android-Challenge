package com.exercise.androidchallenge.repository.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class DBMainHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    DBMainHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            db = getWritableDatabase();
        }
        return db;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            db.close();
        }
    }

}