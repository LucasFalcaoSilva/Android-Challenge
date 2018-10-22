package com.exercise.androidchallenge.repository.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.repository.db.DBMainHelper;

public class UsuarioDao extends BaseDao<Usuario> {

    public static final String TABLE_NAME = "USUARIO";

    public static final String ID = "ID";
    private static final String NOME = "Nome";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

    public UsuarioDao(DBMainHelper helper) {
        super(helper);
    }

    public static String getCreateTableQuery() {

        return "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + ID + " TEXT PRIMARY KEY,"
                + NOME + " TEXT,"
                + USERNAME + " TEXT,"
                + EMAIL + " TEXT" + ")";

    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected ContentValues toContentValues(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(ID, usuario.getId());
        values.put(NOME, usuario.getNome());
        values.put(USERNAME, usuario.getUsername());
        values.put(EMAIL, usuario.getEmail());

        return values;
    }

    @Override
    protected Usuario toObject(Cursor c) {
        Usuario usuario = new Usuario();
        usuario.setId(c.getLong(c.getColumnIndex(ID)));
        usuario.setEmail(c.getString(c.getColumnIndex(EMAIL)));
        usuario.setNome(c.getString(c.getColumnIndex(NOME)));
        usuario.setUsername(c.getString(c.getColumnIndex(USERNAME)));
        return usuario;
    }
}
