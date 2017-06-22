package com.sergey.androidhomework.presenter;


import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sergey.androidhomework.model.InfoModel;
import com.sergey.androidhomework.view.InfoView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoPresenterImpl extends MvpNullObjectBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel infoModel;
    private Subscription subscription;

    public InfoPresenterImpl(InfoModel infoModel) {
        this.infoModel = infoModel;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
    }



    @Override
    public void loadInformation() {
        final InfoView infoView = getView();
        infoView.showLoading(false);

        subscription = infoModel.retrieveInfo()
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> list) {
                        infoView.setData(list);
                        infoView.showContent();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        infoView.setData(new ArrayList<String>());
                        infoView.showContent();
                    }
                });
    }

}
