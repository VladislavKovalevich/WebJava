package org.labs.wt.tour.model;

public enum HotelRank {
    RANK1("1*"),
    RANK2("2*"),
    RANK3("3*"),
    RANK4("4*"),
    RANK5("5*");


    private String displName;


    HotelRank(String dispName) {
        this.displName = dispName;
    }
}
