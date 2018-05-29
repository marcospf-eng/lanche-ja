package com.desafiolanchonete.lanchesja;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showMessage(String message);

}
