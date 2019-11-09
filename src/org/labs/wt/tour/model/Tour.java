package org.labs.wt.tour.model;

import org.labs.wt.tour.model.Hotel;

import java.util.Date;

public class Tour {

    private Hotel hotel;
    private Date from;
    private Integer countPerson;
    private Integer countDays;
    private Integer isBookt;

    public Hotel getHotel() {
        return hotel;
    }

    public Date getFrom() {
        return from;
    }

    public Integer getCountPerson() {
        return countPerson;
    }

    public Integer getCountDays() {
        return countDays;
    }


    Tour(Hotel hotel, Date from, Integer countDays, Integer countPerson, Integer isBookt)
    {
        this.countDays = countDays;
        this.countPerson = countPerson;
        this.from = from;
        this.hotel = hotel;
        this.isBookt = isBookt;
    }

    public Integer isBookt() {
        return isBookt;
    }
}
