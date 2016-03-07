package com.mytime.exercise;

import android.util.Log;

import com.mytime.exercise.network.pojo.Deal;
import com.mytime.exercise.transformer.DealTransformer;
import com.mytime.exercise.viewmodel.DealViewModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchPresenter {

    private final String LATITUDE = "34.052200";
    private final String LONGITUDE = "-118.242800";


    private MyTimeService myTimeService;
    private SearchView view;
    private DealTransformer transformer;

    public SearchPresenter(SearchView view) {
        this.view = view;

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(MyTimeService.BASE_URL)
                .build();
        myTimeService = retrofit.create(MyTimeService.class);

        transformer = new DealTransformer(view.getContext());
    }

    public void retrieveDeals() {
        myTimeService.getDeals("Massage", "Anytime", LATITUDE + "," + LONGITUDE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Deal>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("SearchPresenter", "getDeals completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("SearchPresenter", "getDeals error: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Deal> deals) {
                        DealAdapter dealAdapter = new DealAdapter(transformer.transformToViewModel(deals));
                        view.setDealAdapter(dealAdapter);
                    }
                });
    }
}
