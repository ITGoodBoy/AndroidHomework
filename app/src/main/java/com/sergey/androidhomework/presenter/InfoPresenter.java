package com.sergey.androidhomework.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.sergey.androidhomework.view.InfoView;

/**
 * Created by Sergey on 19.06.2017.
 */

public interface InfoPresenter extends MvpPresenter<InfoView> {

    void loadInformation(final boolean pullToRefresh);
}
