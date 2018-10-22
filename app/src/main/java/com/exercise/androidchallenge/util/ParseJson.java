package com.exercise.androidchallenge.util;

import com.exercise.androidchallenge.domain.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    public static List<Usuario> parseJsonUsuarios(String json) {
        List<Usuario> usuarios = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray jsonObj = new JSONArray(json);

                // looping through All Contacts
                for (int i = 0; i < jsonObj.length(); i++) {
                    Usuario usuario = new Usuario();
                    JSONObject c = jsonObj.getJSONObject(i);

                    Long id = c.getLong("id");
                    String name = c.getString("name");
                    String username = c.getString("username");
                    String email = c.getString("email");

                    usuario.setUsername(username);
                    usuario.setEmail(email);
                    usuario.setId(id);
                    usuario.setNome(name);

                    usuarios.add(usuario);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return usuarios;

    }
}
