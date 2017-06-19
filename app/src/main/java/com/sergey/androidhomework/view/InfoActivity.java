package com.sergey.androidhomework.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import com.sergey.androidhomework.R;
import com.sergey.androidhomework.model.InfoModel;
import com.sergey.androidhomework.model.InfoModelImpl;
import com.sergey.androidhomework.presenter.InfoPresenter;
import com.sergey.androidhomework.presenter.InfoPresenterImpl;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoActivity extends MvpLceViewStateActivity<TextView, String, InfoView, InfoPresenter>
        implements InfoView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        InfoModel infoModel = new InfoModelImpl();
        return new InfoPresenterImpl(infoModel);
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean b) {
        String message = throwable.getMessage();
        return message == null ? "Unknown error" : message;

    }

    @Override
    public void setData(String s) {
        contentView.setText(s);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation(pullToRefresh);
    }

    @NonNull
    @Override
    public LceViewState<String, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public String getData() {
        return contentView.getText().toString();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void animateContentViewIn() {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
    }
}
