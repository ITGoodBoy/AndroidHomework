package com.sergey.androidhomework.model;

/**
 * Created by Sergey on 19.06.2017.
 */

public interface MyAction<T> {

    void onSuccess(T t);
    void onError(Exception e);
}
