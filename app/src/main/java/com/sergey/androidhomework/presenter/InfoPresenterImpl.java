package com.sergey.androidhomework.presenter;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sergey.androidhomework.model.InfoModel;
import com.sergey.androidhomework.model.MyAction;
import com.sergey.androidhomework.view.InfoActivity;
import com.sergey.androidhomework.view.InfoView;

import java.util.Random;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoPresenterImpl extends MvpNullObjectBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel infoModel;

    public InfoPresenterImpl(InfoModel infoModel) {
        this.infoModel = infoModel;
    }


    @Override
    public void loadInformation(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);

        infoModel.retrieveInfo(new MyAction<String>() {
            @Override
            public void onSuccess(String s) {
                InfoView infoView = getView();
                if (pullToRefresh) {
                    s = s + "#" + ((int) (Math.random() * 100));
                }
                infoView.setData(s);
                infoView.showContent();
            }

            @Override
            public void onError(final Exception e) {
                getView().showError(e, pullToRefresh);
            }
        });
    }
}
