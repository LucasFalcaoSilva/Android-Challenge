package com.exercise.androidchallenge.rest.domain;

import com.exercise.androidchallenge.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSO {

    private Long id;
    private String nome;
    private String username;
    private String email;

    public UsuarioSO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<Usuario> getUsuarios(List<UsuarioSO> usuariosSO) {
        List<Usuario> usuarios = new ArrayList<>();

        if (usuariosSO == null)
            return new ArrayList<>();

        for (UsuarioSO so : usuariosSO) {

            usuarios.add(convertUsuario(so));

        }

        return usuarios;
    }


    private static Usuario convertUsuario(UsuarioSO usuarioSO) {
        Usuario usuario = new Usuario();

        usuario.setId(usuarioSO.getId());
        usuario.setUsername(usuarioSO.getUsername());
        usuario.setNome(usuarioSO.getNome());
        usuario.setEmail(usuarioSO.getEmail());

        return usuario;

    }
}
