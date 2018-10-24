package com.exercise.androidchallenge.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.exercise.androidchallenge.domain.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    String ID = "id";
    String START_QUERY = "SELECT * FROM USUARIO WHERE ";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Usuario> usuarios);

    @Delete
    void delete(Usuario usuario);

    @Query(START_QUERY + ID + " = :idUsuario")
    Usuario getById(String idUsuario);

    @Query("SELECT * FROM USUARIO")
    List<Usuario> getAll();
}
