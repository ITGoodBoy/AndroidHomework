package com.sergey.androidhomework.model;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoModelImpl implements InfoModel {

    private final List<String> list = new ArrayList<>();

    @Override
    public Observable<List<String>> retrieveInfo() {
        Observable<String> stringObservable = Observable.from(new String[]{"Hello", "World"});
        return Observable
                .range(1, 2)
                .zipWith(stringObservable, new Func2<Integer, String, List<String>>() {
                    @Override
                    public List<String> call(Integer integer, String s) {
                        list.add(integer + s);
                        return list;
                    }
                });
    }
}


