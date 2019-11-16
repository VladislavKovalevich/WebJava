
package org.labs.wt.tour.model;


public class Hotel extends Identifier {

    private Region region;
    private String hotelName;
    private HotelRank rank;
    private FoodType maxFoodType;

    public Hotel() {
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HotelRank getRank() {
        return rank;
    }

    public void setRank(HotelRank rank) {
        this.rank = rank;
    }

    public FoodType getMaxFoodType() {
        return maxFoodType;
    }

    public void setMaxFoodType(FoodType maxFoodType) {
        this.maxFoodType = maxFoodType;
    }

}
