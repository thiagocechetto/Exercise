package com.mytime.exercise.transformer;

import android.content.Context;

import com.mytime.exercise.R;
import com.mytime.exercise.network.pojo.Deal;
import com.mytime.exercise.network.pojo.Location;
import com.mytime.exercise.viewmodel.DealViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DealTransformer {

    public static final float ONE_METER_IN_MILES = 0.000621371f;
    public static final String SCREEN_DATE_FORMAT = "MM/dd/yyyy";
    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private Context context;
    private double currentLatitude;
    private double currentLongitude;
    private SimpleDateFormat screenFormat = new SimpleDateFormat(SCREEN_DATE_FORMAT);
    private SimpleDateFormat jsonFormat = new SimpleDateFormat(JSON_DATE_FORMAT);

    public DealTransformer(Context context) {
        this.context = context;
    }

    public void setCurrentLocation(double latitude, double longitude) {
        currentLatitude = latitude;
        currentLongitude = longitude;
    }

    public List<DealViewModel> transformToViewModel(List<Deal> deals) {
        List<DealViewModel> dealViewModelList = new ArrayList<DealViewModel>(deals.size());
        for (Deal deal : deals) {
            DealViewModel dealViewModel = transformToViewModel(deal);
            dealViewModelList.add(dealViewModel);
        }
        return dealViewModelList;
    }

    DealViewModel transformToViewModel(Deal deal) {

        return new DealViewModel(
                deal.name,
                deal.service_name,
                calculateDistanceFrom(deal.location),
                formatNextAppt(deal.next_appointment_times[0]),
                deal.instant_confirmation,
                deal.on_sale,
                formatPriceRange(deal.min_price, deal.max_price),
                deal.yelp_rating_image_url,
                deal.photo_url
        );
    }

    String calculateDistanceFrom(Location location) {
        float[] resultInMeters = new float[3];
        android.location.Location.distanceBetween(
                currentLatitude,
                currentLongitude,
                Float.parseFloat(location.lat),
                Float.parseFloat(location.lon),
                resultInMeters
        );
        return context.getResources().getString(R.string.miles_away, metersToMiles(resultInMeters[0]));
    }

    float metersToMiles(float meters) {
        return meters * ONE_METER_IN_MILES;
    }

    String formatNextAppt(String nextAppointment) {
        if (nextAppointment.isEmpty()) {
            return "";
        }
        String result = "";
        try {
            Date date = jsonFormat.parse(nextAppointment);
            result = screenFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return context.getResources().getString(R.string.next_appt, result);
    }

    String formatPriceRange(int minPrice, int maxPrice) {
        return context.getResources().getString(R.string.price_range, minPrice, maxPrice);
    }
}
