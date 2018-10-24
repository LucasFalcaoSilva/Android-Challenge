package com.exercise.androidchallenge.exercise.model;

import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.exercise.ExerciseMVP;
import com.exercise.androidchallenge.repository.UsuarioRepository;
import com.exercise.androidchallenge.rest.domain.UsuarioSO;
import com.exercise.androidchallenge.rest.service.UsuarioWService;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class ExerciseModel implements ExerciseMVP.ExerciseModel {

    private ExerciseMVP.ExercisePresenter exercisePresenter;

    @Bean
    protected UsuarioWService usuarioWService;

    @Bean
    protected UsuarioRepository usuarioRepository;

    @Override
    public void setPresenter(ExerciseMVP.ExercisePresenter exercisePresenter) {
        this.exercisePresenter = exercisePresenter;
    }

    @Override
    @Background
    public void buscarUsuarios() {


        List<UsuarioSO> usuariosSO = usuarioWService.obterFilmes();

        List<Usuario> usuarios = UsuarioSO.getUsuarios(usuariosSO);

        usuarioRepository.save(usuarios);

        exercisePresenter.carregarUsuarios();

    }

    @Override
    public void apagarTodosUsuarios() {
        usuarioRepository.deleteALL();
    }

    @Override
    public int obterQuantidadeUsuarios() {
        return usuarioRepository.getALL().size();
    }
}
