package com.exercise.androidchallenge.rest.service;


import com.exercise.androidchallenge.base.BaseService;
import com.exercise.androidchallenge.rest.domain.UsuarioSO;
import com.exercise.androidchallenge.rest.retrofit.UsuarioRest;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.List;

import retrofit2.Call;


@EBean
public class UsuarioWService extends BaseService {

    private UsuarioRest filmeRest;

    @AfterInject
    protected void iniciar() {
        filmeRest = (UsuarioRest) retrofitCreate(UsuarioRest.class, "www.somaku.com");
    }

    public List<UsuarioSO> obterFilmes() {
        List<UsuarioSO> result = null;
        try {

            Call<List<UsuarioSO>> call = filmeRest.receberUsuarios();

            result = call.execute().body();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
