package com.exercise.androidchallenge.rest;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection {

    public interface Resultado {
        void sucesso(String retorno);

        void falha();
    }

    private String caminho = "http://www.somaku.com/users";
    private Resultado resultado;

    public void obterUsuarios(final Resultado resultado) {
        this.resultado = resultado;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL endpoint = new URL(caminho);

                    HttpURLConnection myConnection = (HttpURLConnection) endpoint.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        resultado.sucesso(getResultadoConexao(myConnection.getInputStream()));
                    } else {
                        resultado.falha();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getResultadoConexao(InputStream responseBody) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(responseBody));
        StringBuilder total = new StringBuilder();
        for (String line; (line = r.readLine()) != null; ) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
