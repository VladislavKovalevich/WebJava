package org.labs.wt.tour.model;

public class Hotel {
    private Region region;
    private String hotelName;

    public Region getRegion() {
        return region;
    }

    public String getHotelName() {
        return hotelName;
    }
   // private FoodType foodType;
   // private HotelRank hotelRank;

    public Hotel(String hotelName, Region region){
        this.hotelName = hotelName;
        this.region = region;
    }
}
