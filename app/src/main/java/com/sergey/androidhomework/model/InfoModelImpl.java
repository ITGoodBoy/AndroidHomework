package com.sergey.androidhomework.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoModelImpl implements InfoModel {

    private final List<String> list = new ArrayList<>();

    @NonNull
    @Override
    public Observable<List<String>> retrieveInfo() {
        Observable<String> stringObservable = Observable.from(new String[]{"Hello", "World"});
        return Observable
                .range(1, 2)
                .zipWith(stringObservable, (integer, s) -> {
                    list.add(integer + s);
                    return list;
                });
    }
}


