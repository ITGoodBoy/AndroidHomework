package com.sergey.androidhomework.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import com.sergey.androidhomework.R;
import com.sergey.androidhomework.model.InfoModel;
import com.sergey.androidhomework.model.InfoModelImpl;
import com.sergey.androidhomework.presenter.InfoPresenter;
import com.sergey.androidhomework.presenter.InfoPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sergey on 19.06.2017.
 */

public class InfoActivity extends MvpLceViewStateActivity<RecyclerView, List<String>, InfoView, InfoPresenter>
        implements InfoView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh_layout)
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        InfoModel infoModel = new InfoModelImpl();
        return new InfoPresenterImpl(infoModel);
    }

    @Nullable
    @Override
    protected String getErrorMessage(Throwable throwable, boolean b) {
        String message = throwable.getMessage();
        return message == null ? "Unknown error" : message;

    }

    @Override
    public void setData(@NonNull final List<String> list) {
        contentView.setLayoutManager(new LinearLayoutManager(InfoActivity.this));
        contentView.setAdapter(new InfoAdapter(list));
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation();
    }

    @NonNull
    @Override
    public LceViewState<List<String>, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @NonNull
    @Override
    public List<String> getData() {
        return ((InfoAdapter) contentView.getAdapter()).getList();
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
    public void showError(final Throwable e, final boolean pullToRefresh) {
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
