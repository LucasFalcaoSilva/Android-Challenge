package com.exercise.androidchallenge.rest.retrofit;

import com.exercise.androidchallenge.rest.domain.UsuarioSO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioRest {

    @GET("/users")
    Call<List<UsuarioSO>> receberUsuarios();

}
