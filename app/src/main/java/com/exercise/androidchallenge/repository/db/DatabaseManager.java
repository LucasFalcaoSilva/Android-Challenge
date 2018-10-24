package com.exercise.androidchallenge.repository.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.exercise.androidchallenge.repository.dao.UsuarioDao;

public class DatabaseManager {
    private static Database dbInstance;
    private static DatabaseManager instance;
    private static final String DATABASE_NAME = "DBchallenge.db";

    public static DatabaseManager getInstance(Context context) {

        if (instance == null)
            instance = new DatabaseManager();

        if (dbInstance == null)
            initDB(context);

        return instance;
    }

    public static void initDB(Context context) {
        dbInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static void destroyInstance() {
        instance = null;
        dbInstance = null;
    }

    public UsuarioDao getUsuarioDAO() {
        return dbInstance.getUsuarioDAO();
    }

}
