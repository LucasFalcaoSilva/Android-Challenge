package com.exercise.androidchallenge.repository.db;

import android.arch.persistence.room.RoomDatabase;

import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.repository.dao.UsuarioDao;

@android.arch.persistence.room.Database(entities = {Usuario.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract UsuarioDao getUsuarioDAO();

}
