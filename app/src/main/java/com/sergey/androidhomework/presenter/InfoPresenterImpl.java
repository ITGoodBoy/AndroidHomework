package com.sergey.androidhomework.presenter;


import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sergey.androidhomework.model.InfoModel;
import com.sergey.androidhomework.view.InfoView;

import java.util.ArrayList;

import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoPresenterImpl extends MvpNullObjectBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel infoModel;
    private Subscription subscription;

    public InfoPresenterImpl(@NonNull InfoModel infoModel) {
        this.infoModel = infoModel;
    }

    @Override
    public void loadInformation() {
        final InfoView infoView = getView();
        infoView.showLoading(false);

        subscription = infoModel.retrieveInfo()
                .observeOn(Schedulers.immediate())
                .subscribe(list -> {
                    infoView.setData(list);
                    infoView.showContent();
                }, throwable -> {
                    infoView.setData(new ArrayList<>());
                    infoView.showContent();
                });
    }



    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

}
