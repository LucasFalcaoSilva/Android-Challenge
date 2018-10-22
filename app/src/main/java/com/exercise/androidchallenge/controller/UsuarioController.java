package com.exercise.androidchallenge.controller;

import android.content.Context;

import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.repository.dao.UsuarioDao;
import com.exercise.androidchallenge.repository.db.DatabaseHelper;

import java.util.List;

public class UsuarioController {

    private UsuarioDao usuarioDao;
    private DatabaseHelper databaseHelper;
    private Context mContext;

    public UsuarioController( Context mContext){
        this.mContext = mContext;
        databaseHelper = new DatabaseHelper(mContext);
        usuarioDao = new UsuarioDao(databaseHelper);
    }


    public List<Usuario> getALL(){
        return usuarioDao.getAll();
    }

    public void save(List<Usuario> usuarios){

        if (usuarios == null || usuarios.isEmpty()) return;

        usuarioDao.save(usuarios);

    }

    public void deleteALL() {
        usuarioDao.deleteALL();
    }
}
