package com.mytime.exercise.viewmodel;

public class DealViewModel {

    private String name;
    private String type;
    private String distance;
    private String nextAppt;
    private boolean showInstantConfirmationIcon;
    private boolean showOnSaleIcon;
    private String priceRange;
    private String yelpRatingUrl;

    public DealViewModel(String name, String type, String distance, String nextAppt, boolean showInstantConfirmationIcon, boolean showOnSaleIcon, String priceRange, String yelpRatingUrl) {
        this.name = name;
        this.type = type;
        this.distance = distance;
        this.nextAppt = nextAppt;
        this.showInstantConfirmationIcon = showInstantConfirmationIcon;
        this.showOnSaleIcon = showOnSaleIcon;
        this.priceRange = priceRange;
        this.yelpRatingUrl = yelpRatingUrl;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDistance() {
        return distance;
    }

    public String getNextAppt() {
        return nextAppt;
    }

    public boolean isShowInstantConfirmationIcon() {
        return showInstantConfirmationIcon;
    }

    public boolean isShowOnSaleIcon() {
        return showOnSaleIcon;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getYelpRatingUrl() {
        return yelpRatingUrl;
    }
}
