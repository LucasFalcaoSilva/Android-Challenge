package com.exercise.androidchallenge.repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.exercise.androidchallenge.repository.db.DBMainHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {

    private final String TAG = "Base";
    protected DBMainHelper dbHelper;
    protected SQLiteDatabase db;


    public BaseDao(DBMainHelper helper) {
        dbHelper = helper;
    }

    protected DBMainHelper getDbHelper() {
        return dbHelper;
    }

    protected SQLiteDatabase openDatabase() {
        return dbHelper.openDatabase();
    }

    protected void closeDatabase() {
        dbHelper.closeDatabase();
    }

    public Long save(T object) {

        try {
            SQLiteDatabase db = openDatabase();
            Long id = db.replace(getTableName(), null, toContentValues(object));

            return id;

        } catch (Exception e) {
            Log.e(TAG, "Falha ao salvar", e);
            return 0l;
        } finally {
            closeDatabase();
        }
    }

    public Long saveIfNew(T object) {

        try {
            SQLiteDatabase db = openDatabase(); //getDbHelper().getWritableDatabase();
            Long id = db.insert(getTableName(), null, toContentValues(object));
            return id;

        } catch (Exception e) {
            Log.e(TAG, "Falha ao salvar", e);
            return 0l;
        } finally {
            closeDatabase();
        }

    }

    public List<Long> save(List<T> objectList) {

        List<Long> ids = new ArrayList<>();

        if (objectList == null) return ids;

        SQLiteDatabase db = openDatabase();
        db.beginTransaction();

        try {

            for (T object : objectList) {
                Long id = db.replace(getTableName(), null, toContentValues(object));
                ids.add(id);
            }

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e(TAG, "Falha ao salvar", e);

        } finally {
            db.endTransaction();
            closeDatabase();
        }

        return ids;
    }


    public T getById(Long id) {

        if (id == null) return null;

        try {

            SQLiteDatabase db = openDatabase();
            Cursor result = db.query(getTableName(), null, "ID = ?", new String[]{id.toString()}, null, null, null);

            if (result.getCount() == 0) return null;

            result.moveToFirst();
            T object = toObject(result);
            result.close();

            return object;

        } catch (Exception e) {
            Log.e(TAG, "Falha ao recuperar registro", e);
            return null;
        } finally {
            closeDatabase();

        }

    }

    public boolean deleteById(Long id) {

        try {

            SQLiteDatabase db = openDatabase(); //getDbHelper().getWritableDatabase();
            int rows = db.delete(getTableName(), "ID = ?", new String[]{id.toString()});

            return rows > 0;

        } catch (Exception e) {
            Log.e(TAG, "Falha ao excluir registro", e);
            return false;
        } finally {
            closeDatabase();
        }


    }

    public List<T> getAll() {
        return query(null);
    }

    public List<T> toList(Cursor c) {

        List<T> list = new ArrayList<T>();

        if (c.moveToFirst()) {
            do {
                list.add(toObject(c));
            } while (c.moveToNext());
        }

        return list;
    }

    public List<T> query(String selection, String... args) {

        SQLiteDatabase db = openDatabase(); //getDbHelper().getReadableDatabase();

        Cursor result = db.query(getTableName(), null, selection, args, null, null, null);
        List<T> list = toList(result);

        result.close();

        closeDatabase();

        return list;

    }

    public long count() {
        db = openDatabase();
        long c = DatabaseUtils.queryNumEntries(db, getTableName());
        closeDatabase();
        return c;
    }

    protected abstract String getTableName();

    protected abstract ContentValues toContentValues(T object);

    protected abstract T toObject(Cursor c);

}
