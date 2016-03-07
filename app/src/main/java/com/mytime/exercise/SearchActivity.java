package com.mytime.exercise;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter presenter;

    @Bind(R.id.deal_list)
    RecyclerView dealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        dealList.setLayoutManager(new LinearLayoutManager(this));

        presenter = new SearchPresenter(this);
        presenter.retrieveDeals();
    }

    @Override
    public void setDealAdapter(DealAdapter dealAdapter) {
        dealList.setAdapter(dealAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
