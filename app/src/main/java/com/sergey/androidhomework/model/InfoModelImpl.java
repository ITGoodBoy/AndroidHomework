package com.sergey.androidhomework.model;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoModelImpl implements InfoModel {
    @Override
    public Observable<List<String>> retrieveInfo() {
        return Observable.timer(1L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<List<String>>>() {
            @Override
            public Observable<List<String>> call(Long aLong) {
                Observable<List<String>> result;

                List<String> list = new ArrayList<>();
                list.add("some string 1");
                list.add("some string 2");

                if(Math.random() > 0.5) {
                    result = Observable.just(list);
                }
                else {
                    result = Observable.error(new IllegalStateException());
                }
                return result;
            }
        });
    }
}


