
package org.labs.wt.tour.model;


public class Transport extends Identifier {

    private Tour tour;
    private TransportType type;
    private Country from;
    private Country to;


    public Transport() {
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public Country getFrom() {
        return from;
    }

    public void setFrom(Country from) {
        this.from = from;
    }

    public Country getTo() {
        return to;
    }

    public void setTo(Country to) {
        this.to = to;
    }

}
