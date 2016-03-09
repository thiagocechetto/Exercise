package com.mytime.exercise.network.pojo;

import java.util.ArrayList;
import java.util.List;

public class Deal {

    public String name;
    public String photo_url;
    public String yelp_rating_image_url;
    public String service_name;
    public int min_price;
    public int max_price;
    public String[] next_appointment_times = new String[14];
    public Location location;
    public boolean instant_confirmation;
    public boolean on_sale;

    public Deal() {

        next_appointment_times = new String[14];
        for (int i = 0; i < next_appointment_times.length; i++) {
            next_appointment_times[i] = "";
        }

    }
}
