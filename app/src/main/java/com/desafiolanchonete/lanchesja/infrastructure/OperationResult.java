package com.desafiolanchonete.lanchesja.infrastructure;

public class OperationResult<TResult> {

    public enum Type {
        SUCCESS, ERROR
    }

    private TResult mResult;
    private Type mType;

    public OperationResult() {
        /* Do nothing */
    }

    public TResult getResult() {
        return mResult;
    }

    public void setResult(TResult result) {
        mResult = result;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

}
