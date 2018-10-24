package com.exercise.androidchallenge.repository;

import android.content.Context;

import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.repository.dao.UsuarioDao;
import com.exercise.androidchallenge.repository.db.DatabaseManager;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class UsuarioRepository {

    @RootContext
    protected Context mContext;

    private UsuarioDao usuarioDao;

    @AfterInject
    protected void init() {
        usuarioDao = DatabaseManager.getInstance(mContext).getUsuarioDAO();
    }

    public List<Usuario> getALL() {
        return usuarioDao.getAll();
    }

    public void save(List<Usuario> usuarios) {

        if (usuarios == null || usuarios.isEmpty()) return;

        usuarioDao.insertAll(usuarios);

    }

    public void deleteALL() {
        usuarioDao.deleteALL();
    }
}
