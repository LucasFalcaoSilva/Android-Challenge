package com.exercise.androidchallenge.exercise;

public interface ExerciseMVP {
    interface ExerciseView {
        void carregarQuantidade(final int size);
    }

    interface ExercisePresenter {
        void setView(ExerciseMVP.ExerciseView exerciseView);

        void buscarUsuarios();

        void carregarUsuarios();

        void apagarUsuarios();
    }

    interface ExerciseModel {
        void setPresenter(ExerciseMVP.ExercisePresenter exercisePresenter);

        void buscarUsuarios();

        void apagarTodosUsuarios();

        int obterQuantidadeUsuarios();
    }
}
