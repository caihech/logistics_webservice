package com.api.webservice.utils.exception;

/**
 * Created by hs on 2017/4/13.
 */
public class CustomException extends RuntimeException {
    private Object object;

    public Object getObject() {
        return object;
    }

    public CustomException setObject(Object object) {
        this.object = object;
        return this;
    }
}
