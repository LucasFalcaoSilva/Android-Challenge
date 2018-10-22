package com.exercise.androidchallenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.androidchallenge.controller.UsuarioController;
import com.exercise.androidchallenge.domain.Usuario;
import com.exercise.androidchallenge.rest.Connection;
import com.exercise.androidchallenge.util.ParseJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private Button btnBuscar;
    private Button btnApagar;
    private TextView txtQuantidade;

    private Context mContext;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mContext = this;

        init();
        load();

        carregarUsuarios();
    }

    private void init() {
        txtQuantidade = findViewById(R.id.txtQuantidade);
        btnApagar = findViewById(R.id.btnApagar);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuarios();
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apagarUsuarios();
            }
        });
    }

    private void load() {
        usuarioController = new UsuarioController(mContext);
    }

    private void buscarUsuarios() {

        Toast.makeText(mContext, "Buscando usuarios", Toast.LENGTH_SHORT).show();
        Connection connection = new Connection();
        connection.obterUsuarios(new Connection.Resultado() {
            @Override
            public void sucesso(String retorno) {
                List<Usuario> usuarios = ParseJson.parseJsonUsuarios(retorno);

                usuarioController.save(usuarios);

                carregarUsuarios();
            }

            @Override
            public void falha() {
                Toast.makeText(mContext, "Não foi possivel obter usuarios", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void carregarUsuarios() {
        int size = usuarioController.getALL().size();
        carregarQuantidade(size);
    }

    private void apagarUsuarios() {
        usuarioController.deleteALL();
        Toast.makeText(mContext, "Todos os usuarios apagados", Toast.LENGTH_SHORT).show();
        carregarUsuarios();
    }

    private void carregarQuantidade(final int size) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                txtQuantidade.setText("Quantidade de Usuarios:" + size);
            }
        });
    }
}
