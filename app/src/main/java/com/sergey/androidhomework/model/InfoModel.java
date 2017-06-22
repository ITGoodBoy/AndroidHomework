package com.sergey.androidhomework.model;

import java.util.List;

import rx.Observable;

/**
 * Created by Sergey on 19.06.2017.
 */

public interface InfoModel {
    Observable<List<String>> retrieveInfo();
}

