package com.desafiolanchonete.lanchesja.data.Business;

import android.os.Handler;
import android.os.Looper;

import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;

public abstract class BaseBusiness {

    public <T> void callbackExecution(final OperationResult<T> operationResult, final OperationListener<T> operationListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (operationResult.getType() == OperationResult.Type.SUCCESS) {
                    operationListener.onSuccess(operationResult.getResult());
                } else {
                    operationListener.onError();
                }
            }
        });
    }

}
