package com.exercise.androidchallenge.exercise.presenter;

import android.content.Context;
import android.widget.Toast;

import com.exercise.androidchallenge.exercise.ExerciseMVP;
import com.exercise.androidchallenge.exercise.model.ExerciseModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ExercisePresenter implements ExerciseMVP.ExercisePresenter {

    private ExerciseMVP.ExerciseView exerciseView;

    @RootContext
    protected Context mContext;

    @Bean
    protected ExerciseModel exerciseModel;

    @AfterInject
    public void init() {
        exerciseModel.setPresenter(this);
    }

    @Override
    public void setView(ExerciseMVP.ExerciseView exerciseView) {
        this.exerciseView = exerciseView;
    }

    @Override
    public void buscarUsuarios() {
        Toast.makeText(mContext, "Buscando usuarios", Toast.LENGTH_SHORT).show();
        exerciseModel.buscarUsuarios();
    }

    @Override
    public void carregarUsuarios() {
        int size = exerciseModel.obterQuantidadeUsuarios();
        exerciseView.carregarQuantidade(size);
    }

    @Override
    public void apagarUsuarios() {
        exerciseModel.apagarTodosUsuarios();
        Toast.makeText(mContext, "Todos os usuarios apagados", Toast.LENGTH_SHORT).show();
        carregarUsuarios();
    }
}
