
package org.labs.wt.tour.model;


public enum FoodType {

    RO("Room only"),
    BB("Bad & Breakfast"),
    HB("Half Board"),
    FB("Full Board"),
    AI("All Inclusive");

    private String desc;

    FoodType(String desc) {
        this.desc = desc;
    }

}
