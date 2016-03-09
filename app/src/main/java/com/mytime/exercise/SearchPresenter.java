package com.mytime.exercise;

import android.location.Location;
import android.util.Log;

import com.mytime.exercise.network.pojo.Deal;
import com.mytime.exercise.transformer.DealTransformer;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchPresenter {

    public static final String WHAT_MASSAGE = "Massage";
    public static final String WHEN_ANYTIME = "Anytime";

    // used when GPS fails:
    public static final double DEFAULT_LATITUDE = 34.052200;
    public static final double DEFAULT_LONGITUDE = -118.242800;


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

    public void retrieveDeals(Location location) {

        String locationString;

        if (location == null) {
            transformer.setCurrentLocation(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
            view.showMessage(R.string.gps_error_message);
            locationString = DEFAULT_LATITUDE + "," + DEFAULT_LONGITUDE;
        } else {
            transformer.setCurrentLocation(location.getLatitude(), location.getLongitude());
            locationString = location.getLatitude() + "," + location.getLongitude();
        }
        myTimeService.getDeals(WHAT_MASSAGE, WHEN_ANYTIME, locationString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Deal>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("SearchPresenter", "getDeals completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("SearchPresenter", "getDeals error: " + e.getLocalizedMessage());
                        view.showMessage(R.string.error_retrieving_deals_message);
                    }

                    @Override
                    public void onNext(List<Deal> deals) {
                        if (deals.isEmpty()) {
                            view.showMessage(R.string.no_deals_message);
                        }
                        DealAdapter dealAdapter = new DealAdapter(transformer.transformToViewModel(deals));
                        view.setDealAdapter(dealAdapter);
                    }
                });
    }
}
