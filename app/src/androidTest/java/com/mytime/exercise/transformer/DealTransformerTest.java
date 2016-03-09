package com.mytime.exercise.transformer;

import android.test.AndroidTestCase;
import com.mytime.exercise.network.pojo.Location;

public class DealTransformerTest extends AndroidTestCase {
    DealTransformer transformer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        transformer = new DealTransformer(getContext());
        transformer.setCurrentLocation(34.052200, -118.242800);
    }

    public void testFormatNextAppt() {
        // Given...
        String nextAppointment = "2016-03-07T18:00:00Z";

        // When...
        String result = transformer.formatNextAppt(nextAppointment);

        // Then...
        assertEquals("Next appt 03/07/2016", result);
    }

    public void testFormatPriceRange() {
        // Given...
        int minPrice = 30;
        int maxPrice = 80;

        // When...
        String result = transformer.formatPriceRange(minPrice, maxPrice);

        // Then...
        assertEquals("$30 - $80", result);
    }

    public void testCalculateDistanceFrom() {
        // Given...
        Location location = new Location();
        location.lat = "34.052200";
        location.lon = "-118.242800";

        // When...
        String result = transformer.calculateDistanceFrom(location);

        // Then...
        assertEquals("0.0 mi away", result);

        // Given...
        location = new Location();
        location.lat = "34.069028";
        location.lon = "-118.252016";

        // When...
        result = transformer.calculateDistanceFrom(location);

        // Then...
        assertEquals("1.3 mi away", result);
    }

}
