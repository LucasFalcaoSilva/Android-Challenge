package com.exercise.androidchallenge.exercise.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.exercise.androidchallenge.R;
import com.exercise.androidchallenge.exercise.ExerciseMVP;
import com.exercise.androidchallenge.exercise.presenter.ExercisePresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

@EActivity(R.layout.activity_exercise)
public class ExerciseActivity extends AppCompatActivity implements ExerciseMVP.ExerciseView {

    @ViewById(R.id.txtQuantidade)
    protected TextView txtQuantidade;

    @Bean
    protected ExercisePresenter exercisePresenter;

    @AfterInject
    public void init() {
        exercisePresenter.setView(this);

        exercisePresenter.carregarUsuarios();
    }


    @Click(R.id.btnBuscar)
    protected void callBuscarUsuarios() {
        exercisePresenter.buscarUsuarios();
    }

    @Click(R.id.btnApagar)
    protected void callApagarUsuarios() {
        exercisePresenter.apagarUsuarios();
    }

    @UiThread
    @Override
    public void carregarQuantidade(final int size) {
        txtQuantidade.setText(String.format(Locale.getDefault(), "%s%d", getString(R.string.qtd_usuarios), size));
    }
}
