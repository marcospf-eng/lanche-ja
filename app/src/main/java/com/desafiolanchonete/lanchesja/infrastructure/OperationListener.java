package com.desafiolanchonete.lanchesja.infrastructure;

public interface OperationListener<TResult> {

    void onSuccess(TResult result);

    void onError();

}
